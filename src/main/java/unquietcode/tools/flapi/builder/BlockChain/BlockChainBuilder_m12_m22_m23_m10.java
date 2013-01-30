
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m24_m9_m10;


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
public interface BlockChainBuilder_m12_m22_m23_m10 <_ReturnType >{


    BlockChainBuilder_m12_m22_m23_m10 <BlockChainBuilder_m22_m23_m10 <_ReturnType>> addBlockChain();

    _ReturnType addBlockReference(String blockName);

    BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType> startBlock();

    BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType> startBlock(String blockName);

}
