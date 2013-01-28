
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on January 27, 2013 22:16:43 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 27, 2013 22:16:43 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder_m11_m20_m10
    implements BlockChainBuilder_m11_m20_m10, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    ImplBlockChainBuilder_m11_m20_m10(BlockChainHelper helper, Object returnValue) {
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

    public BlockChainBuilder_m11_m20_m10 addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
        ImplBlockChainBuilder_m20_m10 step1 = new ImplBlockChainBuilder_m20_m10(_helper, _returnValue);
        ImplBlockChainBuilder_m11_m20_m10 step2 = new ImplBlockChainBuilder_m11_m20_m10(helper1 .get(), step1);
         
        _transferInvocations(step2);
        return step2;
    }

    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockBuilder_m1_m2_m21_m9_m10 startBlock(String blockName) {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, helper1);
        ImplBlockBuilder_m1_m2_m21_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m21_m9_m10(helper1 .get(), _returnValue);
         
        return step1;
    }

}
