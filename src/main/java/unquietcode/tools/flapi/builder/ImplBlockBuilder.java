
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public class ImplBlockBuilder
    implements BlockBuilder
{

    protected final BlockHelper _helper;
    protected final Object _returnValue;

    ImplBlockBuilder(BlockHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public MethodBuilder_addBlockChain addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
         
        MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        return step1;
    }

    public MethodBuilder_addBlockChain addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
         
        MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        return step1;
    }

    public Object endBlock() {
        _helper.endBlock();
         
        return _returnValue;
    }

    public MethodBuilder_addBlockChain startBlock(String blockName, String methodSignature) {
	    ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
	    ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);


        BlockBuilder_addBlockChain step2 = new ImplBlockBuilder_addBlockChain(helper2 .get(), this);
	    MethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1.get(), step2);
        return step1;
    }

}
