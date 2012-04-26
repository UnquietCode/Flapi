
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_showLog<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> showLog(boolean value);

}
