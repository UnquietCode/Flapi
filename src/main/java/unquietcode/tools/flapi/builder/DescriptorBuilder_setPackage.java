package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface DescriptorBuilder_setPackage<_ReturnType> {
	_ReturnType build();
	MethodBuilder<DescriptorBuilder_setPackage<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<DescriptorBuilder_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

	DescriptorBuilder<_ReturnType> setPackage(String packageName);
}