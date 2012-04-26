
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public class ImplBlockChainBuilder
    implements BlockChainBuilder
{

    protected final BlockChainHelper _helper;
    protected final Object _returnValue;

    ImplBlockChainBuilder(BlockChainHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public Object addBlockReference(String blockName) {
        _helper.addBlockReference(blockName);
         
        return _returnValue;
    }

    public BlockBuilder_addBlockChain startBlock(String methodSignature, String blockName) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(methodSignature, blockName, helper1);
         
        BlockBuilder_addBlockChain step1 = new ImplBlockBuilder_addBlockChain(helper1 .get(), _returnValue);
        return step1;
    }

}
