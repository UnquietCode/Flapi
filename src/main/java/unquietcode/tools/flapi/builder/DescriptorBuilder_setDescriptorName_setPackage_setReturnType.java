
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
 * Generated on May 28, 2012 19:55:23 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 19:55:23 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_setDescriptorName_setPackage_setReturnType<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setPackage_setReturnType<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_exitWhenEmpty<DescriptorBuilder_setDescriptorName_setPackage_setReturnType<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setPackage_setReturnType<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType> setPackage(String packageName);

    DescriptorBuilder_setDescriptorName_setPackage<_ReturnType> setReturnType(Class returnType);

}
