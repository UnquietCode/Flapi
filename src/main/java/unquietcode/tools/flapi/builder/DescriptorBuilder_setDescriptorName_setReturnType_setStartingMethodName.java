
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 17:01:37 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 17:01:37 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_exitWhenEmpty<DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType_setStartingMethodName<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName_setStartingMethodName<_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType> setStartingMethodName(String methodName);

}
