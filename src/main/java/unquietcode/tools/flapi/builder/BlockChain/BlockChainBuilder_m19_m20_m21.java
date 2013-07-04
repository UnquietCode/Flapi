
package unquietcode.tools.flapi.builder.BlockChain;

import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;
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
 * Generated on July 03, 2013 22:41:52 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 03, 2013 22:41:52 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainBuilder_m19_m20_m21 <_ReturnType> {
    /**
     * add a reference to an existing block
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {
        BlockBuilder_m22 .class
    })
    BlockBuilder_m22 <_ReturnType> startBlock();

    /**
     * create a new block
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {
        BlockBuilder_m22 .class
    })
    BlockBuilder_m22 <_ReturnType> startBlock(String blockName);
}
