
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setReturnType_setPackage_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setReturnType_setPackage_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setReturnType_setPackage_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setPackage_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setReturnType_setStartingMethodName<_ReturnType> setPackage(String packageName);

    DescriptorBuilder_setReturnType_setPackage<_ReturnType> setStartingMethodName(String methodName);

}
