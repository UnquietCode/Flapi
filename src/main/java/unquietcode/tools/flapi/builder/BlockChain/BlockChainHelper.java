
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on January 30, 2013 1:01:45 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 30, 2013 1:01:45 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainHelper {


    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void addBlockReference(String blockName);

    void startBlock(ObjectWrapper<BlockHelper> _helper1);

    void startBlock(String blockName, ObjectWrapper<BlockHelper> _helper1);

}
