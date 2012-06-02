
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
public interface BlockChainBuilder<_ParentType >{


    _ParentType addBlockReference(String blockName);

    BlockBuilder<_ParentType> startBlock(String blockName);

}
