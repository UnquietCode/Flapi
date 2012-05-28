
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
public class ImplBlockBuilder_exitWhenEmpty
    implements BlockBuilder_exitWhenEmpty
{

    private final BlockHelper _helper;
    private final Object _returnValue;

    ImplBlockBuilder_exitWhenEmpty(BlockHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public MethodBuilder_addBlockChain addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
         
        MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        MethodBuilder_addBlockChain retval = step1;
        _transferInvocations(retval);
        return retval;
    }

    public MethodBuilder_addBlockChain addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
         
        MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        MethodBuilder_addBlockChain retval = step1;
        _transferInvocations(retval);
        return retval;
    }

    public Object endBlock() {
        _checkInvocations();
        _helper.endBlock();
         
        Object retval = _returnValue;
        return retval;
    }

    public MethodBuilder_addBlockChain startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
         
        BlockBuilder_exitWhenEmpty step2 = new ImplBlockBuilder_exitWhenEmpty(helper2 .get(), this);
        MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), step2);
        MethodBuilder_addBlockChain retval = step1;
        _transferInvocations(retval);
        return retval;
    }

    public BlockBuilder exitWhenEmpty(boolean value) {
        _helper.exitWhenEmpty(value);
         
        BlockBuilder retval = new ImplBlockBuilder(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
