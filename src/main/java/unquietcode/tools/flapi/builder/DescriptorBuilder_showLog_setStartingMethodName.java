
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_showLog_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setStartingMethodName<_ReturnType> showLog(boolean value);

    DescriptorBuilder_showLog<_ReturnType> setStartingMethodName(String methodName);

}
