
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m26_m9_m10;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Block.ImplBlockBuilder_m1_m2_m26_m9_m10;
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
 * Generated on February 02, 2013 12:47:50 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 12:47:50 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder_m11_m24_m25_m10
    implements BlockChainBuilder_m11_m24_m25_m10, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    public ImplBlockChainBuilder_m11_m24_m25_m10(BlockChainHelper helper, Object returnValue) {
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

    public BlockChainBuilder_m11_m24_m25_m10 addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
        ImplBlockChainBuilder_m24_m25_m10 step1 = new ImplBlockChainBuilder_m24_m25_m10(_helper, _returnValue);
        ImplBlockChainBuilder_m11_m24_m25_m10 step2 = new ImplBlockChainBuilder_m11_m24_m25_m10(helper1 .get(), step1);
         
        _transferInvocations(step2);
        return step2;
    }

    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockBuilder_m1_m2_m26_m9_m10 startBlock() {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(helper1);
        ImplBlockBuilder_m1_m2_m26_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m26_m9_m10(helper1 .get(), _returnValue);
         
        return step1;
    }

    public BlockBuilder_m1_m2_m26_m9_m10 startBlock(String blockName) {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, helper1);
        ImplBlockBuilder_m1_m2_m26_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m26_m9_m10(helper1 .get(), _returnValue);
         
        return step1;
    }

}
