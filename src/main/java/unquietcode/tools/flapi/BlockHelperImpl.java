package unquietcode.tools.flapi;

import unquietcode.Pair;
import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class BlockHelperImpl implements BlockHelper {
	final BlockOutline block;

	public BlockHelperImpl(BlockOutline block) {
		this.block = block;
	}
	
	@Override
	public MethodHelper addMethod(String methodSignature) {
		MethodHelperImpl helper = new MethodHelperImpl();
		MethodOutline newMethod = helper.method;
		block.methods.add(newMethod);
		newMethod.methodSignature = methodSignature;
		
		return helper;
	}

	@Override
	public List<Object> startBlock(String blockName, String methodSignature) {
		BlockOutline newBlock = new BlockOutline();
		block.blocks.add(newBlock);
		newBlock.name = blockName;

		MethodOutline blockMethod = new MethodOutline();
		blockMethod.methodSignature = methodSignature;
		newBlock.constructor = blockMethod;
		block.methods.add(blockMethod);

		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new MethodHelperImpl(blockMethod));
		helpers.add(new BlockHelperImpl(newBlock));

		return helpers;
	}

	@Override
	public MethodHelper addBlockReference(String blockName, String methodSignature) {
		MethodHelperImpl helper = new MethodHelperImpl();
		BlockReference blockReference = new BlockReference();
		block.blockReferences.add(blockReference);
		blockReference.name = blockName;
		blockReference.constructor = new MethodOutline();
		blockReference.constructor.methodSignature = methodSignature;

		return helper;
	}

	@Override
	public void endBlock() {
		// TODO
	}

	@Override
	public BlockChainHelper addBlockChain() {
		return new BlockChainHelperImpl(block.constructor);
	}
}
