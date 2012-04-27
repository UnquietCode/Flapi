
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

    public BlockBuilder startBlock(String blockName, String methodSignature) {
        ObjectWrapper<BlockHelper> helper1 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1);
         
        BlockBuilder step1 = new ImplBlockBuilder(helper1 .get(), _returnValue);
        return step1;
    }

}
