package unquietcode.tools.flapi.builder;

import unquietcode.Pair;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class ImplBlockBuilder<_ReturnType> implements BlockBuilder<_ReturnType> {
	private final BlockHelper _helper;
	private final _ReturnType _returnValue;
	
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
	public MethodBuilder<BlockBuilder_addBlockChain<BlockBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature) {
		List<Object> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder_addBlockChain<BlockBuilder<_ReturnType>> returnBlock = new ImplBlockBuilder_addBlockChain<BlockBuilder<_ReturnType>>((BlockHelper) helpers.get(1), this);
		return new ImplMethodBuilder<BlockBuilder_addBlockChain<BlockBuilder<_ReturnType>>>((MethodHelper) helpers.get(0), returnBlock);
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
}
