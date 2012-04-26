
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setStartingMethodName_setPackage<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setStartingMethodName_setPackage<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setStartingMethodName_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setPackage<_ReturnType> setStartingMethodName(String methodName);

    DescriptorBuilder_setStartingMethodName<_ReturnType> setPackage(String packageName);

}
