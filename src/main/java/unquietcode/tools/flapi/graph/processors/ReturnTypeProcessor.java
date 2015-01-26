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

import com.sun.codemodel.JClass;
import com.sun.codemodel.JType;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.*;
import unquietcode.tools.flapi.java.JavaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class ReturnTypeProcessor extends AbstractGenerator {

	public ReturnTypeProcessor(GeneratorContext context) {
		super(context);
	}


	public JType computeReturnType(Transition transition) {
		List<StateClass> sequentialStates = new ArrayList<StateClass>(transition.getStateChain());

		// setup initial return value
		final AtomicReference<JType> initialType = new AtomicReference<JType>();

		transition.accept(new TransitionVisitor.$() {
			@Override
			public void visit(AscendingTransition transition) {
				JClass sc = BUILDER_OR_WRAPPER_NARROWED_INTERFACE_STRATEGY.createWeakType(ctx, transition.getOwner());
				initialType.set(sc.getTypeParameters().get(0));
			}

			@Override
			public void visit(LateralTransition transition) {
				initialType.set(BUILDER_OR_WRAPPER_NARROWED_INTERFACE_STRATEGY.createWeakType(ctx, transition.getSibling()));
			}

			@Override
			public void visit(RecursiveTransition transition) {
				initialType.set(BUILDER_OR_WRAPPER_NARROWED_INTERFACE_STRATEGY.createWeakType(ctx, transition.getSelf()));
			}

			@Override
			public void visit(TerminalTransition transition) {
				JavaType type = transition.getReturnType() == null
							  ? JavaType.from(Void.class)
							  : transition.getReturnType();

				// force the use of primitive void where appropriate
				if (type.isVoid() && transition.getStateChain().isEmpty()) {
					initialType.set(ctx.model.VOID);
				} else {
					initialType.set(getType(type));
				}
			}
		});

		// add the intermediate states
		JType returnType = initialType.get();
		
		for (int i = sequentialStates.size()-1; i >= 0; --i) {
			StateClass sequentialState = sequentialStates.get(i);
			JClass targetBuilder = BUILDER_OR_WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, sequentialState);
			returnType = targetBuilder.narrow(returnType);
		}

		return returnType;
	}
}
