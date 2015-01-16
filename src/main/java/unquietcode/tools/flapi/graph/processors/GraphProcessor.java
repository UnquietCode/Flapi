/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.graph.processors;

import com.google.common.base.Function;
import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.LateralTransition;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.TerminalTransition;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.runtime.ChainInfo;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.Tracked;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;


public class GraphProcessor extends AbstractGenerator implements GenericVisitor<StateClass> {
	private Set<StateClass> seen = Collections.newSetFromMap(new IdentityHashMap<StateClass, Boolean>());

	public GraphProcessor(GeneratorContext context) {
		super(context);
	}

	public JCodeModel generate(StateClass startingClass) {
		visit(startingClass);
		return ctx.model;
	}

	@Override
	public void visit(StateClass state) {
		if (seen.contains(state)) {
			return;
		} else {
			seen.add(state);
		}

		// create the interface class
		JDefinedClass iBuilder = BUILDER_INTERFACE_STRATEGY.createStrongType(ctx, state);

		for (Transition transition : state.getTransitions()) {

			// add methods to interface
			ReturnTypeProcessor rt = new ReturnTypeProcessor(ctx);
			JType returnType = rt.computeReturnType(transition);
			JMethod _method = addMethod(iBuilder, returnType, JMod.NONE, transition);

			final JAnnotationUse infoAnnotation = _method.annotate(MethodInfo.class);
			infoAnnotation.param("type", transition.getType());

			transition.accept(new TransitionVisitor.$() {
				public void visit(LateralTransition transition) {
					if (!transition.getStateChain().isEmpty()) {
						JClass next = BUILDER_OR_WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, transition.getSibling());
						infoAnnotation.param("next", next);
					}
				}
			});

			// store the type information for the state chain
			if (!transition.getStateChain().isEmpty()) {
				final List<Integer> chainParameterPositions = transition.getChainParameterPositions();
				final Function<Integer, Integer> positionFunction;

				// if no specific parameters, start after the last regular parameter
				if (transition.getChainParameterPositions().isEmpty()) {
					final int offset = new MethodParser(transition.getMethodSignature()).params.size();

					positionFunction = new Function<Integer, Integer>() {
						public Integer apply(Integer idx) {
							return offset + idx;
						}
					};

				// otherwise, use the provided position
				} else {
					positionFunction = new Function<Integer, Integer>() {
						public Integer apply(Integer idx) {
							return chainParameterPositions.get(idx);
						}
					};
				}

				List<StateClass> stateChain = transition.getStateChain();
				JAnnotationArrayMember chain = infoAnnotation.paramArray("chainInfo");

				for (int i=0; i < stateChain.size(); i++) {
					StateClass sc = stateChain.get(i);
					JClass type = BUILDER_OR_WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, sc);
					final int position = positionFunction.apply(i);

					JAnnotationUse chainInfo = chain.annotate(ChainInfo.class);
					chainInfo.param("type", type);
					chainInfo.param("position", position);
				}
			}

			// if it's an atLeast method, requiring tracking
			if (transition.info().getMinOccurrences() > 0) {
				String key = transition.info().keyString();
				key = key.substring(0, key.length()-2); // removes the t/f triggered portion

				_method.annotate(Tracked.class)
					.param("atLeast", transition.info().getMinOccurrences())
					.param("key", key)
				;
			}

			// add the helper method to helper interface,
			// but only if there isn't an existing helper
			if (transition.getOwner().getHelperClass() == null) {
				addHelperCall(transition);
			}

			// continue to the next states
			transition.acceptForTraversal(this);
		}
	}

	private void addHelperCall(Transition transition) {
		if (ctx.helperMethods.seen(transition)) { return; }

		// get a return value if present
		final AtomicReference<JType> helperReturnType = new AtomicReference<JType>();

		transition.accept(new TransitionVisitor.$() {
			public @Override void visit(TerminalTransition transition) {
				String clazz = transition.getReturnType() == null || transition.info().isImplicit()
						     ? Void.class.getName()
							 : transition.getReturnType();

				// Set the return type unless it's (V)oid, in which case as a convenience we
				// set the return to (v)oid, which is also done on the *Helper interfaces.

				if (!clazz.equals(Void.class.getName())) {
					helperReturnType.set(ctx.model.ref(clazz));
				}
			}
		});

		// add the helper method to the helper interface
		JType helperReturnType1 = helperReturnType.get();
		JDefinedClass iHelper = HELPER_INTERFACE_STRATEGY.createStrongType(ctx, transition.getOwner());
		JType methodCallType = helperReturnType1 == null ? ctx.model.VOID : helperReturnType1;
		JMethod _method = addHelperMethod(iHelper, methodCallType, JMod.NONE, transition);

		for (int i=0; i < transition.getStateChain().size(); ++i) {
			JClass type = HELPER_INTERFACE_STRATEGY.createWeakType(ctx, transition.getStateChain().get(i));
			_method.param(ref(AtomicReference.class).narrow(type), Constants.HELPER_VALUE_NAME+(i+1));
		}
	}
}