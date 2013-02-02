
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m26_m9_m10;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 02, 2013 13:11:40 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 13:11:40 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m11_m24_m25_m10 <_ReturnType >{


    /**
     * add an additional link to the chain, occurring before this one
     * 
     */
    BlockChainBuilder_m11_m24_m25_m10 <BlockChainBuilder_m24_m25_m10 <_ReturnType>> addBlockChain();

    /**
     * add a reference to an existing block
     * 
     */
    _ReturnType addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     * 
     */
    BlockBuilder_m1_m2_m26_m9_m10 <_ReturnType> startBlock();

    /**
     * create a new block
     * 
     */
    BlockBuilder_m1_m2_m26_m9_m10 <_ReturnType> startBlock(String blockName);

}
