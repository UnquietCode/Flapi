
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog_setReturnType_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog_setReturnType_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_showLog_setReturnType_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setStartingMethodName_setReturnType<_ReturnType> showLog(boolean value);

    DescriptorBuilder_showLog_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_showLog_setReturnType<_ReturnType> setStartingMethodName(String methodName);

}
