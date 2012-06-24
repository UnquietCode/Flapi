
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
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_enableCondensedClassNames<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_enableCondensedClassNames<_ReturnType>> addMethod(String methodSignature);

    Descriptor build();

    MethodBuilder_addBlockChain<BlockBuilder<DescriptorBuilder_enableCondensedClassNames<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder<_ReturnType> enableCondensedClassNames();

}
