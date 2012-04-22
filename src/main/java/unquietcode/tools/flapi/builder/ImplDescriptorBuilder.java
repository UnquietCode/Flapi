package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;

import java.util.List;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
@SuppressWarnings("unchecked")
public class ImplDescriptorBuilder implements DescriptorBuilder {
	protected final DescriptorHelper _helper;
	protected final Descriptor _returnValue;

	ImplDescriptorBuilder(DescriptorHelper helper) {
		_helper = helper;
		_returnValue = new Descriptor(_helper);
	}

	public Descriptor build() {
		return _returnValue;
	}

	@Override
	public MethodBuilder addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder(helper, this);
	}

	@Override
	public MethodBuilder startBlock(String blockName, String methodSignature) {
		List<Object> helpers = _helper.startBlock(blockName, methodSignature);
		BlockBuilder_addBlockChain innerBlock = new ImplBlockBuilder_addBlockChain((BlockHelper) helpers.get(1), this);
		return new ImplMethodBuilder((MethodHelper) helpers.get(0), innerBlock);
	}
}
