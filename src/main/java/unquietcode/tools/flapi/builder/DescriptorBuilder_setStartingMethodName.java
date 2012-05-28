
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 24:33:22 CDT using version 0.2
 * 
 */
public interface DescriptorBuilder_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> setStartingMethodName(String methodName);

}
