package unquietcode.tools.flapi.builder;

import unquietcode.Pair;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockBuilder_addBlockChain<_ReturnType> implements BlockBuilder_addBlockChain<_ReturnType> {
	protected final BlockHelper _helper;
	protected final _ReturnType _returnValue;

	ImplBlockBuilder_addBlockChain(BlockHelper helper, _ReturnType returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}

	@Override
	public MethodBuilder<BlockBuilder_addBlockChain<_ReturnType>> addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder<BlockBuilder_addBlockChain<_ReturnType>>(helper, this);
	}

	@Override
	public MethodBuilder<BlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>>> startBlock(String blockName, String methodSignature) {
		List<Object> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>> returnBlock = new ImplBlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>>((BlockHelper) helpers.get(1), this);
		return new ImplMethodBuilder<BlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>>>((MethodHelper) helpers.get(0), returnBlock);
	}



	@Override
	public MethodBuilder<BlockBuilder_addBlockChain<_ReturnType>> addBlockReference(String blockName, String methodSignature) {
		MethodHelper mHelper = _helper.addBlockReference(blockName, methodSignature);
		return new ImplMethodBuilder<BlockBuilder_addBlockChain<_ReturnType>>(mHelper, this);
	}

	@Override
	public _ReturnType endBlock() {
		_helper.endBlock();
		return _returnValue;
	}

	@Override
	public BlockChainBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>> addBlockChain() {
		return null;
	}
}
