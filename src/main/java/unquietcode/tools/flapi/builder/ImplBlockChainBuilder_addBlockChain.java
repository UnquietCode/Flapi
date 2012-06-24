
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder_addBlockChain
    implements BlockChainBuilder_addBlockChain, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public BuilderImplementation addBlockReference(String blockName) {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockBuilder startBlock(String blockName) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.startBlock(blockName, helper1);
         
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _returnValue);
        return step1;
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        ImplBlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), new ImplBlockChainBuilder(_helper, _returnValue));
        _transferInvocations(step1);
        return step1;
    }

}
