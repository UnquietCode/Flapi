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
		ref.setName(blockName);
		ref.setConstructor(blockMethod);
		blockMethod.getBlockChain().add(0, ref);

	}

	@Override
	public void startBlock(String methodSignature, String blockName, ObjectWrapper<BlockHelper> _helper1) {
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
