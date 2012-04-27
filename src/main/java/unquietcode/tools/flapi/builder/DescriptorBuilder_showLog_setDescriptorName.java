
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog_setDescriptorName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog_setDescriptorName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_showLog_setDescriptorName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setDescriptorName<_ReturnType> showLog(boolean value);

    DescriptorBuilder_showLog<_ReturnType> setDescriptorName(String descriptorName);

}
