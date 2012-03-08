package unquietcode.tools.flapi.builder;

import unquietcode.Pair;
import unquietcode.tools.flapi.Descriptor;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder<_SelfType> implements DescriptorBuilder<_SelfType> {
	protected final DescriptorHelper _helper;

	@SuppressWarnings("unchecked")
	protected final _SelfType _returnValue = (_SelfType) this;

	ImplDescriptorBuilder(DescriptorHelper helper) {
		_helper = helper;
	}

	@Override
	public Descriptor build() {
		return new Descriptor(_helper);
	}

	@Override
	public MethodBuilder<_SelfType> addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder<_SelfType>(helper, _returnValue);
	}

	@Override
	public MethodBuilder<BlockBuilder<_SelfType>> startBlock(String blockName, String methodSignature) {
		Pair<MethodHelper, BlockHelper> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder<_SelfType> innerBlock = new ImplBlockBuilder<_SelfType>(helpers.second, _returnValue);
		return new ImplMethodBuilder<BlockBuilder<_SelfType>>(helpers.first, innerBlock);
	}
	
	/* 
		what this is saying is:
			
			start block (return BlockBuilder)
			but first you have to go through a MethodBuilder
			and then when its done you can continue
	*/
}
