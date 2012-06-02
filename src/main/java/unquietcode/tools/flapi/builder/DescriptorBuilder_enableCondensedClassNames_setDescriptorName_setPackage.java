
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 01, 2012 21:44:52 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 01, 2012 21:44:52 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage<_ParentType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage<_ParentType>> addMethod(String methodSignature);

    Descriptor build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage<_ParentType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setDescriptorName_setPackage<_ParentType> enableCondensedClassNames();

    DescriptorBuilder_enableCondensedClassNames_setPackage<_ParentType> setDescriptorName(String descriptorName);

    DescriptorBuilder_enableCondensedClassNames_setDescriptorName<_ParentType> setPackage(String packageName);

}
