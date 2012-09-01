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

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ExpectedInvocationsException;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

/**
* @author Ben Fagin
* @version 08-12-2012
*/
public class BuilderClassTypeCreationStrategy implements TypeCreationStrategy {

	@Override
	public JDefinedClass createType(GeneratorContext ctx, StateClass state) {
		final String name = ctx.getGeneratedName("Impl", "Builder", state);

		if (ctx.doesClassExist(name)) {
			return ctx.getOrCreateClass(name);
		}

		AbstractGeneratorParent gen = new AbstractGeneratorParent(ctx);
		JDefinedClass cBuilder = ctx.getOrCreateClass(name);
		gen.createType(cBuilder, state);
		return cBuilder;
	}

	private static class AbstractGeneratorParent extends AbstractGenerator {
		AbstractGeneratorParent(GeneratorContext ctx) {
			super(ctx);
		}

		void createType(JDefinedClass cBuilder, StateClass state) {
			JDefinedClass iBuilder = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);
			JDefinedClass iHelper = HELPER_INTERFACE_STRATEGY.createType(ctx, state);
			cBuilder._implements(iBuilder);
			cBuilder._implements(ref(BuilderImplementation.class));

			JType returnType = ref(Object.class);
			JFieldVar _helper = cBuilder.field(JMod.PRIVATE+JMod.FINAL, iHelper, Constants.HELPER_VALUE_NAME);
			JFieldVar _returnValue = cBuilder.field(JMod.PRIVATE+JMod.FINAL, returnType, Constants.RETURN_VALUE_NAME);

			// constructor
			JMethod constructor = cBuilder.constructor(JMod.NONE);
			JVar pHelper = constructor.param(iHelper, "helper");
			JVar pParent = constructor.param(returnType, "returnValue");

			constructor.body().assign(_helper, pHelper);
			constructor.body().assign(_returnValue, pParent);

			// BuilderImplementation._getParent()
			JMethod _getParent = cBuilder.method(JMod.PUBLIC, ref(BuilderImplementation.class), "_getParent");
			JConditional l_if = _getParent.body()._if(_returnValue._instanceof(ref(BuilderImplementation.class)));
			l_if._then()._return(_returnValue);
			l_if._else()._return(JExpr._null());

			addInvocationTracking(cBuilder, state.getTransitions());
		}

		private void addInvocationTracking(JDefinedClass cBuilder, Set<Transition> transitions) {
			// filter the methods
			Set<Transition> filtered = new TreeSet<Transition>(transitions);
			for (Transition transition : transitions) {
				if (transition.getMinOccurrences() <= 0) {
					filtered.remove(transition);
				}
			}
			transitions = filtered;

			// fields
			for (Transition transition : transitions) {
				cBuilder.field(JMod.NONE, ctx.model.INT, "ic_"+makeMethodKey(transition), JExpr.lit(transition.getMinOccurrences()));
			}

			// create the _transferInvocations method
			JMethod _method = cBuilder.method(JMod.PRIVATE, ctx.model.VOID, "_transferInvocations");
			JVar _next = _method.param(ref(Object.class), "next");

			if (transitions.isEmpty()) {
				_method.body().directStatement("// nothing");
			} else {
				JVar _clazz = _method.body().decl(ref(Class.class), "clazz", _next.invoke("getClass"));

				for (Transition transition : transitions) {
					_method.body().directStatement(" ");
					String key = "ic_"+makeMethodKey(transition);
					JTryBlock block = _method.body()._try();
					JVar _field = block.body().decl(ref(Field.class), "field", _clazz.invoke("getDeclaredField").arg(key));

					// field.setInt(next, ref(id))
					block.body().add(_field.invoke("setInt").arg(_next).arg(JExpr.ref(key)));

					block._catch(ref(Exception.class)).body().directStatement("// nothing");
				}
			}

			// create the _checkInvocations method
			_method = cBuilder.method(JMod.PUBLIC, ctx.model.VOID, "_checkInvocations");

			if (transitions.isEmpty()) {
				_method.body().directStatement("// nothing");
			} else {
				for (Transition transition : transitions) {
					String key = "ic_"+makeMethodKey(transition);
					String message = "Expected at least "+transition.getMinOccurrences()+" invocations of method '"+transition.getMethodSignature()+"'.";

					_method.body()._if(JExpr.ref(key).gt(JExpr.lit(0)))._then()
						._throw(JExpr._new(ref(ExpectedInvocationsException.class)).arg(message));
				}
			}
		}
	}
}
