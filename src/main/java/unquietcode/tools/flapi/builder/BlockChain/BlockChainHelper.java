
package unquietcode.tools.flapi.builder.BlockChain;

import unquietcode.tools.flapi.builder.Block.BlockHelper;

import javax.annotation.Generated;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 14, 2013 21:46:20 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 14, 2013 21:46:20 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainHelper {
    /**
     * add a reference to an existing block
     */
    void addBlockReference(String blockName);

    /**
     * finish creating the block chain for this method
     */
    void end();

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     */
    void startBlock(AtomicReference<BlockHelper> _helper1);

    /**
     * create a new block
     */
    void startBlock(String blockName, AtomicReference<BlockHelper> _helper1);
}
