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

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.BlockReference;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class BlockHelperImpl implements BlockHelper {
	final BlockOutline block;

	public BlockHelperImpl(BlockOutline block) {
		this.block = block;
	}

	@Override
	public void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		_addMethod(block, methodSignature, _helper1);
	}

	static void _addMethod(BlockOutline block, String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		MethodOutline m = block.addMethod(methodSignature);
		_helper1.set(new MethodHelperImpl(m));
	}

	@Override
	public void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		_startBlock(block, blockName, methodSignature, _helper1, _helper2);
	}

	static void _startBlock(BlockOutline block, String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		BlockOutline newBlock = block.addBlock(blockName);
		MethodOutline blockMethod = block.addMethod(methodSignature);
		blockMethod.getBlockChain().add(0, newBlock);
		newBlock.setConstructor(blockMethod);

		_helper1.set(new MethodHelperImpl(blockMethod));
		_helper2.set(new BlockHelperImpl(newBlock));
	}

	@Override
	public void startBlock(String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		_startBlock(block, null, methodSignature, _helper1, _helper2);
	}

	@Override
	public void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		_addBlockReference(block, blockName, methodSignature, _helper1);
	}

	static void _addBlockReference(
		BlockOutline block,
		String blockName,
		String methodSignature,
		ObjectWrapper<MethodHelper> _helper1
	){
		BlockReference blockReference = new BlockReference(block);
		blockReference.setName(blockName);

		MethodOutline blockMethod = block.addMethod(methodSignature);
		blockMethod.getBlockChain().add(blockReference);
		blockReference.setConstructor(blockMethod);

		_helper1.set(new MethodHelperImpl(blockMethod));
	}

	@Override
	public void endBlock() {
		// nothing
	}
}
