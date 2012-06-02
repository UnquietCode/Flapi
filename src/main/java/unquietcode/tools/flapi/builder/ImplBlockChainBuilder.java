
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
 * Generated on June 01, 2012 21:44:52 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 01, 2012 21:44:52 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplBlockChainBuilder
    implements BlockChainBuilder, BuilderImplementation
{

    private final BlockChainHelper _helper;
    private final BuilderImplementation _parent;

    ImplBlockChainBuilder(BlockChainHelper helper, BuilderImplementation parent) {
        _helper = helper;
        _parent = parent;
    }

    public BuilderImplementation _getParent() {
        return _parent;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public BuilderImplementation addBlockReference(String blockName) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.addBlockReference(blockName);
         
        return _parent;
    }

    public BlockBuilder startBlock(String blockName) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.startBlock(blockName, helper1);
         
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _parent);
        return step1;
    }

}
