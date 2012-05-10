
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:25:34 CDT using version 0.1
 * 
 */
public interface DescriptorBuilder_setPackage_setReturnType_showLog<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setPackage_setReturnType_showLog<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setPackage_setReturnType_showLog<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType_showLog<_ReturnType> setPackage(String packageName);

    DescriptorBuilder_setPackage_showLog<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setPackage_setReturnType<_ReturnType> showLog(boolean value);

}
