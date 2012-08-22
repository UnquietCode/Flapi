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
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.outline.GeneratorOutline;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorGenerator extends AbstractGenerator {

	public GeneratorGenerator(GeneratorContext context) {
		super(context);
	}

	public JDefinedClass generate(StateClass topLevel, GeneratorOutline outline) {
		JType returnType = BUILDER_INTERFACE_STRATEGY.createType(ctx, topLevel).narrow(ref(Void.class));
		JDefinedClass returnValue = BUILDER_CLASS_STRATEGY.createType(ctx, topLevel);
		JDefinedClass generator = GENERATOR_CLASS_STRATEGY.createType(ctx, topLevel);
		JDefinedClass helper = HELPER_INTERFACE_STRATEGY.createType(ctx, topLevel);

		// -- add the constructor methods --

		JMethod createMethod = generator.method(JMod.PUBLIC+JMod.STATIC, returnType, outline.methodName);
		createMethod.annotate(SuppressWarnings.class).param("value", "unchecked");
		JVar pHelper = createMethod.param(helper, "helper");

		// check arguments

		// if (helper == null)
		//      throw new IllegalArgumentException("Helper cannot be null.");
		//
		JConditional _if = createMethod.body()._if(pHelper.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(ref(IllegalArgumentException.class)).arg("Helper cannot be null."));
		createMethod.body().directStatement(" ");

		// get base return value and return
		createMethod.body()._return(
			JExpr._new(returnValue)
				.arg(pHelper)
				.arg(JExpr._null())
		);

		return generator;
	}
}
