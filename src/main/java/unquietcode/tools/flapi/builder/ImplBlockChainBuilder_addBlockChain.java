
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public class ImplBlockChainBuilder_addBlockChain
    extends ImplBlockChainBuilder
    implements BlockChainBuilder_addBlockChain
{


    ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        BlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), this);
        return step1;
    }

}
