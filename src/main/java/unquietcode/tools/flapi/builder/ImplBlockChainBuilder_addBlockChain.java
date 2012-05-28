
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 17:01:37 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 17:01:37 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder_addBlockChain
    implements BlockChainBuilder_addBlockChain
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        Object retval = _returnValue;
        return retval;
    }

    public BlockBuilder_exitWhenEmpty startBlock(String blockName) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _checkInvocations();
        _helper.startBlock(blockName, helper1);
         
        BlockBuilder_exitWhenEmpty step1 = new ImplBlockBuilder_exitWhenEmpty(helper1 .get(), _returnValue);
        BlockBuilder_exitWhenEmpty retval = step1;
        return retval;
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        BlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), new ImplBlockChainBuilder(_helper, _returnValue));
        BlockChainBuilder_addBlockChain retval = step1;
        _transferInvocations(retval);
        return retval;
    }

}
