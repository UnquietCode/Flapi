
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog_setReturnType<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog_setReturnType<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_showLog_setReturnType<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType<_ReturnType> showLog(boolean value);

    DescriptorBuilder_showLog<_ReturnType> setReturnType(Class returnType);

}
