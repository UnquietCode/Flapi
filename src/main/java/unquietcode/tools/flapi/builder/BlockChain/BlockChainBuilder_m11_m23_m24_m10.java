
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m25_m9_m10;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 04, 2013 10:10:17 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 04, 2013 10:10:17 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m11_m23_m24_m10 <_ReturnType >{


    /**
     * add an additional link to the chain, occurring before this one
     * 
     */
    BlockChainBuilder_m11_m23_m24_m10 <BlockChainBuilder_m23_m24_m10 <_ReturnType>> addBlockChain();

    /**
     * add a reference to an existing block
     * 
     */
    _ReturnType addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     * 
     */
    BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType> startBlock();

    /**
     * create a new block
     * 
     */
    BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType> startBlock(String blockName);

}
