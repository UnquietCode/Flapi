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

package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.builder.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class DescriptorHelperImpl implements DescriptorHelper {
	final DescriptorOutline outline = new DescriptorOutline();

	@Override
	public Descriptor _getReturnValue() {
		return new Descriptor(this);
	}

	@Override
	public void setDescriptorName(String descriptorName) {
		if (descriptorName == null || descriptorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}

		outline.setDescriptorName(descriptorName);
	}

	@Override
	public void setReturnType(Class returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}

		outline.setReturnType(returnType);
	}

	@Override
	public void setStartingMethodName(String methodName) {
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}

		outline.setCreateMethod(methodName);
	}

	@Override
	public void enableCondensedClassNames(boolean value) {
		outline.enableCondensedNames(value);
	}

	@Override
	public void build() {
		DescriptorValidator validator = new DescriptorValidator(outline);
		validator.validate();

		resolveBlockReferences();
	}

	@Override
	public void setPackage(String packageName) {
		outline.setPackageName(packageName);
	}

	@Override
	public void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		BlockHelperImpl._addMethod(outline.selfBlock, methodSignature, _helper1);
	}

	@Override
	public void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline.selfBlock, blockName, methodSignature, _helper1, _helper2);
	}

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	private void resolveBlockReferences() {
		Map<String, BlockOutline> blocks = new HashMap<String, BlockOutline>();
		_getBlockNames(outline.selfBlock, blocks);
		_resolveBlockReferences(outline.selfBlock, blocks, new IdentityHashMap<BlockOutline, Object>());
	}

	private void _getBlockNames(BlockOutline block, Map<String, BlockOutline> blocks) {
		// references aren't valid names
		if (block instanceof BlockReference) {
			return;
		}

		blocks.put(block.getName(), block);

		for (BlockOutline child : block.getBlocks()) {
			_getBlockNames(child, blocks);
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				_getBlockNames(chain, blocks);
			}
		}
	}

	private void _resolveBlockReferences(BlockOutline block, Map<String, BlockOutline> blocks, IdentityHashMap<BlockOutline, Object> seen) {
		if (seen.containsKey(block)) {
			return;
		} else {
			seen.put(block, null);
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline aBlock : method.getBlockChain()) {
				if (aBlock instanceof BlockReference) {
					BlockReference _aBlock = (BlockReference) aBlock;

					// skip if already resolved
					// we need this in case the descriptor is generated twice
					if (_aBlock.isResolved()) {
						continue;
					}

					BlockOutline actual = blocks.get(aBlock.getName());

					// couldn't find a block under that name
					if (actual == null) {
						StringBuilder sb = new StringBuilder();
						sb.append("Invalid block reference '").append(aBlock.getName()).append("'.\n")
						  .append("Referenced in method ").append(method.getMethodSignature())
						  .append(" of block '").append(block.getName()).append("'.");

						throw new DescriptorBuilderException(sb.toString());
					}

					// set the methods
					aBlock.getAllMethods().addAll(actual.getAllMethods());

					// mark resolved
					_aBlock.setResolved(true);
				}
			}
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				_resolveBlockReferences(chain, blocks, seen);
			}
		}
	}
}
