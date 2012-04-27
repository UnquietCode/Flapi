
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_showLog_setReturnType_setPackage_setStartingMethodName<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_showLog_setReturnType_setDescriptorName_setStartingMethodName<_ReturnType> setPackage(String packageName);

    DescriptorBuilder_showLog_setDescriptorName_setPackage_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_showLog_setReturnType_setDescriptorName_setPackage<_ReturnType> setStartingMethodName(String methodName);

    DescriptorBuilder_setReturnType_setDescriptorName_setPackage_setStartingMethodName<_ReturnType> showLog(boolean value);

}
