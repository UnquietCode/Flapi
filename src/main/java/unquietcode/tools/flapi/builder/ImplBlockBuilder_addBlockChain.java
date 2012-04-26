
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public class ImplBlockBuilder_addBlockChain
    extends ImplBlockBuilder
    implements BlockBuilder_addBlockChain
{


    ImplBlockBuilder_addBlockChain(BlockHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        BlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), this);
        return step1;
    }


}
