
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 24, 2012 14:26:52 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 24, 2012 14:26:52 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder_addBlockChain
    implements BlockChainBuilder_addBlockChain, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, Object returnValue) {
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

    public BlockBuilder startBlock(String blockName) {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, helper1);
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _returnValue);
         
        return step1;
    }

    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
        ImplBlockChainBuilder step1 = new ImplBlockChainBuilder(_helper, _returnValue);
        ImplBlockChainBuilder_addBlockChain step2 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), step1);
         
        _transferInvocations(step2);
        return step2;
    }

}
