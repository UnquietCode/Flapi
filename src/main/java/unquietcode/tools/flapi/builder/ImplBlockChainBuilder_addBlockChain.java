package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockChainBuilder_addBlockChain extends ImplBlockChainBuilder implements BlockChainBuilder_addBlockChain {
	ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, Object returnValue) {
		super(helper, returnValue);
	}

	@Override
	public BlockChainBuilder addBlockChain(String blockName) {
		BlockChainHelper helper = (BlockChainHelper) _helper.addBlockChain();
		return new ImplBlockChainBuilder_addBlockChain(helper, this);
	}
}
