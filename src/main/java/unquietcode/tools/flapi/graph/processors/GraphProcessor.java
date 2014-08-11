/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph.processors;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.LateralTransition;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.TerminalTransition;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.Tracked;

import java.util.Collections;
import java.util.IdentityHashMap;
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
		JDefinedClass iBuilder = BUILDER_OR_WRAPPER_INTERFACE_STRATEGY.createStrongType(ctx, state);

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
				JAnnotationArrayMember chain = infoAnnotation.paramArray("chain");

				for (StateClass sc : transition.getStateChain()) {
					JClass type = BUILDER_OR_WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, sc);
					chain.param(type);
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
				String clazz = transition.getReturnType() == null
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
