package unquietcode.tools.flapi.builder;

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
		// do checks
		// TODO
		return new Descriptor(_helper);
	}

	@Override
	public MethodBuilder<_SelfType> addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder<_SelfType>(helper, _returnValue);
	}

	@Override
	public MethodBuilder<BlockBuilder<_SelfType>> startBlock(String blockName, String methodSignature) {
		BlockHelper bHelper = _helper.startBlock(blockName, methodSignature);
		BlockBuilder<_SelfType> returnBlock = new ImplBlockBuilder<_SelfType>(bHelper, _returnValue);
		MethodHelper mHelper = bHelper._getConstructor();

		return new ImplMethodBuilder<BlockBuilder<_SelfType>>(mHelper, returnBlock);
	}
}
