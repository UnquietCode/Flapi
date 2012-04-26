
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_setPackage<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setPackage<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName<_ReturnType> setPackage(String packageName);

}
