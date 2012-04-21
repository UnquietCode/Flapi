package unquietcode.tools.flapi.builder;


/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockBuilder_addBlockChain extends ImplBlockBuilder implements BlockBuilder_addBlockChain {

	ImplBlockBuilder_addBlockChain(BlockHelper helper, Object returnValue) {
		super(helper,  returnValue);
	}

	@Override
	public BlockChainBuilder_addBlockChain addBlockChain() {
		BlockChainHelper helper = _helper.addBlockChain();
		return new ImplBlockChainBuilder_addBlockChain(helper, this);
	}
}
