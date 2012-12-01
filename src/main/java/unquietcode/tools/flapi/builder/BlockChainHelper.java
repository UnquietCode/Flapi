
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on December 01, 2012 13:14:02 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "December 01, 2012 13:14:02 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainHelper {


    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void addBlockReference(String blockName);

    void startBlock(String blockName, ObjectWrapper<BlockHelper> _helper1);

}
