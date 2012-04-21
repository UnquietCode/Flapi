package unquietcode.tools.flapi.builder;


import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class ImplBlockBuilder implements BlockBuilder {
	protected final BlockHelper _helper;
	protected final Object _returnValue;
	
	ImplBlockBuilder(BlockHelper helper, Object returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}
	
	@Override
	public MethodBuilder addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder(helper, this);
	}

	@Override
	public MethodBuilder startBlock(String blockName, String methodSignature) {
		List<Object> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder_addBlockChain returnBlock = new ImplBlockBuilder_addBlockChain((BlockHelper) helpers.get(1), this);
		return new ImplMethodBuilder((MethodHelper) helpers.get(0), returnBlock);
	}

	@Override
	public MethodBuilder addBlockReference(String blockName, String methodSignature) {
		MethodHelper mHelper = _helper.addBlockReference(blockName, methodSignature);
		return new ImplMethodBuilder(mHelper, this);
	}

	@Override
	public Object endBlock() {
		_helper.endBlock();
		return _returnValue;
	}
}
