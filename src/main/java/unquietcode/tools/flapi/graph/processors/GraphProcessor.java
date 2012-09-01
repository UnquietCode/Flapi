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
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.generator.MethodImplementor;
import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.graph.components.TransitionType;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;


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

		// create the interface and implementation classes
		JDefinedClass iBuilder = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);

		for (Transition transition : state.getTransitions()) {
			MethodParser parsed = new MethodParser(transition.getMethodSignature());

			// add methods to interface
			ReturnTypeProcessor rt = new ReturnTypeProcessor(ctx);
			JType returnType = rt.computeReturnType(transition);
			addMethod(iBuilder, returnType, JMod.NONE, parsed);

			// add methods to class
			addMethodToClass(state, transition);

			// continue to the next states
			transition.acceptForTraversal(this);
		}
	}

	private void addMethodToClass(final StateClass currentState, Transition transition) {
		final MethodImplementor mi = transition.methodImplementor();
		JDefinedClass cBuilder = BUILDER_CLASS_STRATEGY.createType(ctx, currentState);
		JExpression returnValue;

		// create the method on the class
		ReturnTypeProcessor rtv = new ReturnTypeProcessor(ctx);
		JType returnType = transition.getType() == TransitionType.Ascending         // Ascending ==> _ReturnType
						 ? ref(Object.class)                                        // which could be anything,
						 : rtv.computeReturnType(transition).erasure()              // so be flexible.
		;
		MethodParser parsed = new MethodParser(transition.getMethodSignature());
		final JMethod _method = addMethod(cBuilder, returnType, JMod.PUBLIC, parsed);

		// invocation tracking
		if (mi.shouldTrackInvocations()) {
			_method.body().directStatement("--ic_"+makeMethodKey(transition)+";");
		}

		// invocation check (done before helper call)
		if (mi.shouldCheckInvocations()) {
			if (mi.shouldCheckParentInvocations()) {
				JVar _cur = _method.body().decl(ref(BuilderImplementation.class), "cur", JExpr._this());
				JWhileLoop _while = _method.body()._while(_cur.ne(JExpr._null()));
				_while.body().add(_cur.invoke("_checkInvocations"));
				_while.body().assign(_cur, _cur.invoke("_getParent"));
				_method.body().directStatement(" ");
			} else {
				_method.body().invoke("_checkInvocations");
			}
		}

		// return value work, including helper call
		ReturnValueProcessor rvv = new ReturnValueProcessor(ctx);
		returnValue = rvv.computeReturnValue(transition, _method);
		_method.body().directStatement(" ");

		// invocation transfer
		if (mi.shouldTransferInvocations()) {
			_method.body().invoke("_transferInvocations").arg(returnValue);
		}

		// return call
		_method.body()._return(returnValue);
	}
}
