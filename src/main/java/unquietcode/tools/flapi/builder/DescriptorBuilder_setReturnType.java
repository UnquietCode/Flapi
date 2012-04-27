
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setReturnType<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setReturnType<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setReturnType<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> setReturnType(Class returnType);

}
