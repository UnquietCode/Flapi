
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType<_ReturnType> setPackage(String packageName);

    DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage<_ReturnType> setStartingMethodName(String methodName);

    DescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage<_ReturnType> showLog(boolean value);

}
