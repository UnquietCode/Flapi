package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.List;


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
	public List<Object> addMethod(String methodSignature) {
		return _addMethod(block, methodSignature);
	}

	static List<Object> _addMethod(BlockOutline block, String methodSignature) {
		MethodOutline m = block.addMethod(methodSignature);
		ArrayList<Object> helpers = new ArrayList<Object>();
		helpers.add(new MethodHelperImpl(m));
		return helpers;
	}

	@Override
	public List<Object> startBlock(String blockName, String methodSignature) {
		return _startBlock(block, blockName, methodSignature);
	}

	static List<Object> _startBlock(BlockOutline block, String blockName, String methodSignature) {
		BlockOutline newBlock = block.addBlock(blockName);
		MethodOutline blockMethod = block.addMethod(methodSignature);
		blockMethod.blockChain.add(0, newBlock);
		newBlock.constructor = blockMethod;

		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new MethodHelperImpl(blockMethod));
		helpers.add(new BlockHelperImpl(newBlock));

		return helpers;
	}

	@Override
	public List<Object> addBlockReference(String blockName, String methodSignature) {
		BlockReference blockReference = new BlockReference();
		blockReference.name = blockName;

		MethodOutline blockMethod = new MethodOutline();
		blockMethod.methodSignature = methodSignature;
		blockMethod.blockChain.add(blockReference);
		block.methods.add(blockMethod);
		blockReference.constructor = blockMethod;

		MethodHelperImpl helper = new MethodHelperImpl(blockMethod);
		List<Object> helpers = new ArrayList<Object>();
		helpers.add(helper);
		return helpers;
	}

	@Override
	public void endBlock() {
		// nothing
	}

	@Override
	public List<Object> addBlockChain() {
		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new BlockChainHelperImpl(block.constructor));
		return helpers;
	}
}
