
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setPackage<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setPackage<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> setPackage(String packageName);

}
