
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
 * Generated on May 28, 2012 19:55:23 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 19:55:23 CDT", comments = "generated using Flapi, the fluent API generator for Java")
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
         
        return _returnValue;
    }

    public BlockBuilder_exitWhenEmpty startBlock(String blockName) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _checkInvocations();
        _helper.startBlock(blockName, helper1);
         
        BlockBuilder_exitWhenEmpty step1 = new ImplBlockBuilder_exitWhenEmpty(helper1 .get(), _returnValue);
        return step1;
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        BlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), new ImplBlockChainBuilder(_helper, _returnValue));
        _transferInvocations(step1);
        return step1;
    }

}
