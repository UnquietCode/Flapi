
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_showLog_setReturnType_setDescriptorName_setPackage<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_showLog_setReturnType_setDescriptorName_setPackage<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_showLog_setReturnType_setDescriptorName_setPackage<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType_setDescriptorName_setPackage<_ReturnType> showLog(boolean value);

    DescriptorBuilder_showLog_setDescriptorName_setPackage<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_showLog_setReturnType_setPackage<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_showLog_setReturnType_setDescriptorName<_ReturnType> setPackage(String packageName);

}