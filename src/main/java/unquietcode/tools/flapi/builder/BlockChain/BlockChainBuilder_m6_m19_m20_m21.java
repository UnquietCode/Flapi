
package unquietcode.tools.flapi.builder.BlockChain;

import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;
import unquietcode.tools.flapi.support.LateralHint;
import unquietcode.tools.flapi.support.MethodInfo;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 20:13:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 20:13:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m6_m19_m20_m21 <_ReturnType> {
    /**
     * add an additional link to the chain, occurring before this one
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 1, chain = {
        BlockChainBuilder_m6_m19_m20_m21 .class
    })
    @LateralHint(next = BlockChainBuilder_m19_m20_m21 .class)
    BlockChainBuilder_m6_m19_m20_m21 <BlockChainBuilder_m19_m20_m21 <_ReturnType>> addBlockChain();

    /**
     * add a reference to an existing block
     */
    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = 3, chain = {

    })
    _ReturnType addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     */
    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = 3, chain = {
        BlockBuilder_m22 .class
    })
    BlockBuilder_m22 <_ReturnType> startBlock();

    /**
     * create a new block
     */
    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = 3, chain = {
        BlockBuilder_m22 .class
    })
    BlockBuilder_m22 <_ReturnType> startBlock(String blockName);
}
