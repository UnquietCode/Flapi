
package unquietcode.tools.flapi.builder.BlockChain;

import unquietcode.tools.flapi.builder.Block.BlockBuilder_m20;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 14, 2013 21:16:25 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 14, 2013 21:16:25 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m19 <_ReturnType> {
    /**
     * add a reference to an existing block
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    BlockChainBuilder_m19 <_ReturnType> addBlockReference(String blockName);

    /**
     * finish creating the block chain for this method
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType end();

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        BlockBuilder_m20 .class
    })
    BlockBuilder_m20 <BlockChainBuilder_m19 <_ReturnType>> startBlock();

    /**
     * create a new block
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        BlockBuilder_m20 .class
    })
    BlockBuilder_m20 <BlockChainBuilder_m19 <_ReturnType>> startBlock(String blockName);
}
