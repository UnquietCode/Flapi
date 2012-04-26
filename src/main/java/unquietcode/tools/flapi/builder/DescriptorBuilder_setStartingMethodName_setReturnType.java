
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setStartingMethodName_setReturnType<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setStartingMethodName_setReturnType<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setStartingMethodName_setReturnType<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType<_ReturnType> setStartingMethodName(String methodName);

    DescriptorBuilder_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

}
