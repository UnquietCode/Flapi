package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

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
		ref.name = blockName;
		blockMethod.blockChain.add(0, ref);
	}

	@Override
	public BlockHelper startBlock(String blockName) {
		BlockOutline newBlock = new BlockOutline();
		newBlock.name = blockName;
		BlockHelperImpl helper = new BlockHelperImpl(newBlock);
		blockMethod.blockChain.add(0, newBlock);

		return helper;
	}

	@Override
	public BlockChainHelper addBlockChain() {
		return new BlockChainHelperImpl(blockMethod);
	}
}
