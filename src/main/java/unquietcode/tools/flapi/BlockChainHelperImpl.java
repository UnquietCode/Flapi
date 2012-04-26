package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public List<Object> startBlock(String blockName) {
		BlockOutline anonymousBlock = new BlockOutline();
		anonymousBlock.name = blockName;
		blockMethod.blockChain.add(0, anonymousBlock);

		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new BlockHelperImpl(anonymousBlock));
		return helpers;
	}

	@Override
	public List<Object> addBlockChain() {
		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new BlockChainHelperImpl(blockMethod));
		return helpers;
	}
}
