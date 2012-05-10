
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:25:34 CDT using version 0.1
 * 
 */
public class ImplBlockChainBuilder
    implements BlockChainBuilder
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    ImplBlockChainBuilder(BlockChainHelper helper, Object returnValue) {
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

    public BlockBuilder startBlock(String blockName, String methodSignature) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _checkInvocations();
        _helper.startBlock(blockName, methodSignature, helper1);
         
        BlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _returnValue);
        BlockBuilder retval = step1;
        return retval;
    }

}
