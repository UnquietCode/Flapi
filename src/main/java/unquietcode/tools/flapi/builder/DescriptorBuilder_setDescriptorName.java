
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setDescriptorName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> setDescriptorName(String descriptorName);

}
