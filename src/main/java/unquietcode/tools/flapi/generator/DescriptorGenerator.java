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

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import unquietcode.tools.flapi.BlockReference;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorGenerator extends AbstractGenerator<DescriptorOutline, JCodeModel> {
	public DescriptorGenerator(DescriptorOutline outline) {
		super(outline, new GeneratorContext(outline.getPackageName()));
		ctx.condenseNames(outline.shouldEnableCondensedNames());
	}
	
	@Override
	public JCodeModel generate() {
		// create the generator
		GeneratorGenerator generatorGen = new GeneratorGenerator(outline.getGenerator(), ctx);
		generatorGen.generate();

		// add the custom methods to the helper interface
		JDefinedClass helper = getHelperInterface(outline.selfBlock);
		helper.method(JMod.NONE, ref(outline.getReturnType()), "_getReturnValue");

		// now process all blocks
		BlockGenerator blockGen = new BlockGenerator(outline.selfBlock, ctx);
		blockGen.generate();

		return ctx.model;
	}
}
