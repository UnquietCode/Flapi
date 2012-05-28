
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
 * Generated on May 28, 2012 16:00:17 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 16:00:17 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockBuilder<_ReturnType >{


    MethodBuilder_addBlockChain<BlockBuilder<_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_addBlockChain<BlockBuilder<_ReturnType>> addMethod(String methodSignature);

    _ReturnType endBlock();

    MethodBuilder_addBlockChain<BlockBuilder<BlockBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);

}
