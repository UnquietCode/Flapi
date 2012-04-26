package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface DescriptorBuilder_setPackage_showLog<_ReturnType> {
	_ReturnType build();
	MethodBuilder_addBlockChain<DescriptorBuilder_setPackage_showLog<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setPackage_showLog<_ReturnType>>> startBlock(String blockName, String methodSignature);

	DescriptorBuilder_setPackage<_ReturnType> showLog(boolean value);
	DescriptorBuilder_showLog<_ReturnType> setPackage(String packageName);
}
