
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
public class ImplBlockBuilder
    implements BlockBuilder, BuilderImplementation
{

    private final BlockHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplBlockBuilder(BlockHelper helper, BuilderImplementation returnValue) {
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

    public MethodBuilder_addBlockChain addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
         
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        _transferInvocations(step1);
        return step1;
    }

    public MethodBuilder_addBlockChain addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
         
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        _transferInvocations(step1);
        return step1;
    }

    public BuilderImplementation endBlock() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.endBlock();
         
        return _returnValue;
    }

    public MethodBuilder_addBlockChain startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
         
        ImplBlockBuilder step2 = new ImplBlockBuilder(helper2 .get(), this);
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), step2);
        _transferInvocations(step1);
        return step1;
    }

}
