
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setStartingMethodName_setReturnType<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType> setStartingMethodName(String methodName);

}
