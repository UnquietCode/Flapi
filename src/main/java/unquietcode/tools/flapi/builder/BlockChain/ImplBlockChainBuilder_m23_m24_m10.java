
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m25_m9_m10;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Block.ImplBlockBuilder_m1_m2_m25_m9_m10;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public class ImplBlockChainBuilder_m23_m24_m10
    implements BlockChainBuilder_m23_m24_m10, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    public ImplBlockChainBuilder_m23_m24_m10(BlockChainHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    /**
     * add a reference to an existing block
     * 
     */
    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     * 
     */
    public BlockBuilder_m1_m2_m25_m9_m10 startBlock() {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(helper1);
        ImplBlockBuilder_m1_m2_m25_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m25_m9_m10(helper1 .get(), _returnValue);
         
        return step1;
    }

    /**
     * create a new block
     * 
     */
    public BlockBuilder_m1_m2_m25_m9_m10 startBlock(String blockName) {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, helper1);
        ImplBlockBuilder_m1_m2_m25_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m25_m9_m10(helper1 .get(), _returnValue);
         
        return step1;
    }

}
