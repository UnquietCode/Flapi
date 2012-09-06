/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.graph.processors;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.*;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ReturnValueProcessor extends AbstractGenerator {


	public ReturnValueProcessor(GeneratorContext context) {
		super(context);
	}


	public JExpression computeReturnValue(Transition transition, final JMethod method) {
		final List<JVar> helpers = new ArrayList<JVar>();

		for (StateClass sequenceState : transition.getStateChain()) {
			JType wrappedType = ref(ObjectWrapper.class)
				.narrow(HELPER_INTERFACE_STRATEGY.createType(ctx, sequenceState));
			JVar _helper = method.body().decl(wrappedType, "helper"+ (helpers.size() + 1), JExpr._new(wrappedType));
			helpers.add(_helper);
		}

		// add the helper call
		final JVar helperResult = addHelperCall(transition, method, helpers);

		// setup initial return value
		final ObjectWrapper<JExpression> initialValue = new ObjectWrapper<JExpression>();
		final AtomicInteger counter = new AtomicInteger(1);

		transition.accept(new TransitionVisitor() {
			@Override
			public void visit(AscendingTransition transition) {
				initialValue.set(JExpr.ref(Constants.RETURN_VALUE_NAME));
			}

			@Override
			public void visit(LateralTransition transition) {
				JDefinedClass cTargetBuilder = BUILDER_CLASS_STRATEGY.createType(ctx, transition.getSibling());

				initialValue.set(method.body().decl(
					cTargetBuilder, "step"+counter.getAndIncrement(),
					JExpr._new(cTargetBuilder)
						.arg(JExpr.ref(Constants.HELPER_VALUE_NAME))
						.arg(JExpr.ref(Constants.RETURN_VALUE_NAME))
					)
				);
			}

			@Override
			public void visit(RecursiveTransition transition) {
				initialValue.set(JExpr._this());
			}

			@Override
			public void visit(TerminalTransition transition) {
				// null result on a terminal just means that it was void

				if (helperResult != null) {
					initialValue.set(helperResult);
				} else {
					initialValue.set(JExpr._null());
				}
			}
		});

		// add the intermediate states
		JExpression returnValue = initialValue.get();
		for (int i = transition.getStateChain().size()-1; i >= 0; --i) {
			StateClass sequentialState = transition.getStateChain().get(i);
			JDefinedClass cTargetBuilder = BUILDER_CLASS_STRATEGY.createType(ctx, sequentialState);

			returnValue = method.body().decl(
				cTargetBuilder, "step"+counter.getAndIncrement(),
				JExpr._new(cTargetBuilder)
					.arg(helpers.get(i).invoke("get"))
					.arg(returnValue)
			);
		}

		return returnValue;
	}


	// --------- - - - ----- -- -- --------- -- - ----------- -


	private JVar addHelperCall(Transition transition, JMethod method, List<JVar> helpers) {
		// invoke the main helper
		String helperMethodName = new MethodParser(transition.getMethodSignature()).methodName;
		JInvocation helperCall = makeHelperCall(method, helperMethodName);

		// add the wrapped helpers as parameters
		for (JVar helper : helpers) {
			helperCall.arg(helper);
		}

		// get a return value if present
		final ObjectWrapper<JType> helperReturnType = new ObjectWrapper<JType>();
		transition.accept(new TransitionVisitor.$() {
			public @Override void visit(TerminalTransition transition) {
				Class clazz = transition.getReturnType() == null ? Void.class : transition.getReturnType();

				// Set the return type unless it's (V)oid, in which case as a convenience we
				// set the return to (v)oid, which is also done on the *Helper interfaces.

				if (!clazz.equals(Void.class)) {
					helperReturnType.set(ctx.model.ref(clazz));
				}
			}
		});

		// add the helper method to the helper interface, only for the top level
		if (transition.getOwner().isTopLevel()) {
			addMethodsToHelper(transition, helperReturnType.get(), helpers);
		}

		// add the helper call to method body, capturing the results if necessary
		if (helperReturnType.get() != null) {
			return method.body().decl(helperReturnType.get(), Constants.RESULT_VALUE_NAME, helperCall);
		} else {
			method.body().add(helperCall);
			return null;
		}
	}

	private void addMethodsToHelper(Transition transition, JType helperReturnType, List<JVar> helpers) {
		JDefinedClass iHelper = HELPER_INTERFACE_STRATEGY.createType(ctx, transition.getOwner());
		JType methodCallType = helperReturnType == null ? ctx.model.VOID : helperReturnType;
		MethodParser parsed = new MethodParser(transition.getMethodSignature());
		JMethod _method = addMethod(iHelper, methodCallType, JMod.NONE, parsed);

		int i=1;
		for (JVar helper : helpers) {
			_method.param(helper.type(), Constants.HELPER_VALUE_NAME+(i++));
		}
	}

	private JInvocation makeHelperCall(JMethod _method, String methodName) {
		JFieldRef _helper = JExpr.ref(Constants.HELPER_VALUE_NAME);
		JInvocation helperCall = _helper.invoke(methodName);

		// normal args
		for (JVar param : _method.listParams()) {
			helperCall.arg(param);
		}

		// vararg
		if (_method.listVarParam() != null) {
			helperCall.arg(_method.listVarParam());
		}

		return helperCall;
	}
}
