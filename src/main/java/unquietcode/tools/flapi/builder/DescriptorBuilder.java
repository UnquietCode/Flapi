
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);

}
