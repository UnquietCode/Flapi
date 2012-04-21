package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface DescriptorBuilder<_SelfType> {
	Descriptor build();
	MethodBuilder<_SelfType> addMethod(String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<_SelfType>> startBlock(String blockName, String methodSignature);
}