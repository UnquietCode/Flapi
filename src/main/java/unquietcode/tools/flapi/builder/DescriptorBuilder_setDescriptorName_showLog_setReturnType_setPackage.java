
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_showLog_setReturnType_setPackage<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName_setReturnType_setPackage<_ReturnType> showLog(boolean value);

    DescriptorBuilder_setDescriptorName_showLog_setPackage<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setDescriptorName_showLog_setReturnType<_ReturnType> setPackage(String packageName);

}
