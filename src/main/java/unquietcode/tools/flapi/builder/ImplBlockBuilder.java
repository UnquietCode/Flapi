package unquietcode.tools.flapi.builder;

import unquietcode.Pair;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplBlockBuilder<_ReturnType> implements BlockBuilder<_ReturnType> {
	protected final BlockHelper _helper;
	protected final _ReturnType _returnValue;
	
	ImplBlockBuilder(BlockHelper helper, _ReturnType returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}
	
	@Override
	public MethodBuilder<BlockBuilder<_ReturnType>> addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder<BlockBuilder<_ReturnType>>(helper, this);
	}

	@Override
	public MethodBuilder<BlockBuilder<BlockBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature) {
		Pair<MethodHelper, BlockHelper> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder<BlockBuilder<_ReturnType>> returnBlock = new ImplBlockBuilder<BlockBuilder<_ReturnType>>(helpers.second, this);
		return new ImplMethodBuilder<BlockBuilder<BlockBuilder<_ReturnType>>>(helpers.first, returnBlock);
	}

	@Override
	public MethodBuilder<BlockBuilder<_ReturnType>> addBlockReference(String blockName, String methodSignature) {
		MethodHelper mHelper = _helper.addBlockReference(blockName, methodSignature);
		return new ImplMethodBuilder<BlockBuilder<_ReturnType>>(mHelper, this);
	}

	@Override
	public _ReturnType endBlock() {
		_helper.endBlock();
		return _returnValue;
	}

	@Override
	public BlockChainBuilder<BlockChainBuilder_addBlockChain, BlockBuilder<_ReturnType>> addBlockChain() {
		return null;
	}
}
