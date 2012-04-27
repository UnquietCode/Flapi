
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setReturnType_setDescriptorName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setReturnType_setDescriptorName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setReturnType_setDescriptorName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setDescriptorName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setReturnType<_ReturnType> setDescriptorName(String descriptorName);

}
