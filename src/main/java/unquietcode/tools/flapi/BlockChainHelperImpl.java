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

import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class BlockChainHelperImpl implements BlockChainHelper {
	final MethodOutline blockMethod;

	BlockChainHelperImpl(MethodOutline blockMethod) {
		this.blockMethod = blockMethod;
	}

	/*
		add the reference to the chain (to be resolved later)
	*/
	@Override
	public void addBlockReference(String blockName) {
		BlockReference ref = new BlockReference();
		ref.setName(blockName);
		ref.setConstructor(blockMethod);
		blockMethod.getBlockChain().add(0, ref);

	}

	@Override
	public void startBlock(String blockName, ObjectWrapper<BlockHelper> _helper1) {
		BlockOutline anonymousBlock = new BlockOutline();
		anonymousBlock.setName(blockName);
		blockMethod.getBlockChain().add(0, anonymousBlock);

		_helper1.set(new BlockHelperImpl(anonymousBlock));
	}

	@Override
	public void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(blockMethod));
	}
}
