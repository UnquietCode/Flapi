
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
 * Generated on June 01, 2012 21:44:52 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 01, 2012 21:44:52 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockBuilder<_ParentType >{


    MethodBuilder_addBlockChain<BlockBuilder<_ParentType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_addBlockChain<BlockBuilder<_ParentType>> addMethod(String methodSignature);

    _ParentType endBlock();

    MethodBuilder_addBlockChain<BlockBuilder<BlockBuilder<_ParentType>>> startBlock(String blockName, String methodSignature);

}
