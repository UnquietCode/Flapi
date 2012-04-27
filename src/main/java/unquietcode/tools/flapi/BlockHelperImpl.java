package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
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
	public void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		BlockReference blockReference = new BlockReference();
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

	@Override
	public void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(block.getConstructor()));
	}
}
