
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m22_m9_m10;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on January 29, 2013 21:38:13 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 29, 2013 21:38:13 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m11_m21_m10 <_ReturnType >{


    BlockChainBuilder_m11_m21_m10 <BlockChainBuilder_m21_m10 <_ReturnType>> addBlockChain();

    _ReturnType addBlockReference(String blockName);

    BlockBuilder_m1_m2_m22_m9_m10 <_ReturnType> startBlock(String blockName);

}
