
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public class ImplMethodBuilder_addBlockChain
    extends ImplMethodBuilder
    implements MethodBuilder_addBlockChain
{


    ImplMethodBuilder_addBlockChain(MethodHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        BlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), this);
        return step1;
    }

}
