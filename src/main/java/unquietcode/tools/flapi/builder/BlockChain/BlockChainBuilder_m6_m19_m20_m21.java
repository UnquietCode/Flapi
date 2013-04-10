
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 09, 2013 20:26:31 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 09, 2013 20:26:31 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m6_m19_m20_m21 <_ReturnType> {
    /**
     * add an additional link to the chain, occurring before this one
     */
    BlockChainBuilder_m6_m19_m20_m21 <BlockChainBuilder_m19_m20_m21 <_ReturnType>> addBlockChain();

    /**
     * add a reference to an existing block
     */
    _ReturnType addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     */
    BlockBuilder_m22 <_ReturnType> startBlock();

    /**
     * create a new block
     */
    BlockBuilder_m22 <_ReturnType> startBlock(String blockName);
}
