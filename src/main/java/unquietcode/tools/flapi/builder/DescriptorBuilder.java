package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface DescriptorBuilder<_ReturnType> {
	_ReturnType build();
	MethodBuilder_addBlockChain<DescriptorBuilder<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);
}