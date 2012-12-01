
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 25, 2012 17:55:21 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 25, 2012 17:55:21 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder
    implements BlockChainBuilder, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final Object _returnValue;

    ImplBlockChainBuilder(BlockChainHelper helper, Object returnValue) {
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

    public Object addBlockReference(String blockName) {
        _checkInvocations();
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockBuilder startBlock(String blockName) {
        _checkInvocations();
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, helper1);
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _returnValue);
         
        return step1;
    }

}
