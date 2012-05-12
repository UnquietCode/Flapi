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
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-11-2012
 */
public class BlockGenerator extends AbstractGenerator<BlockOutline, Void> {

	public BlockGenerator(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public Void generate() {
		JDefinedClass iHelper = generateHelper();

		BlockGenerator_Subsets subsetsGen = new BlockGenerator_Subsets(outline, ctx);
		subsetsGen.generate();

		for (BlockOutline child : outline.getBlocks()) {
			BlockGenerator childGenerator = new BlockGenerator(child, ctx);
			childGenerator.generate();
		}

		return null;

	}

	public JDefinedClass generateHelper() {
		JDefinedClass iHelper = getHelperInterface(outline);

		for (MethodOutline method : outline.getAllMethods()) {
			JMethod _method = addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);

			// for every block in the chain, add a wrapped helper parameter
			int i=1;
			for (BlockOutline block : method.getBlockChain()) {
				JDefinedClass blockHelper = getHelperInterface(block);
				_method.param(ref(ObjectWrapper.class).narrow(blockHelper), "_helper"+(i++));
			}
		}

		return iHelper;
	}
}
