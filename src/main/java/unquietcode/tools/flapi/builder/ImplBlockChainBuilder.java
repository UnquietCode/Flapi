package unquietcode.tools.flapi.builder;


/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockChainBuilder implements BlockChainBuilder {
	protected final BlockChainHelper _helper;
	protected final Object _returnValue;

	ImplBlockChainBuilder(BlockChainHelper helper, Object returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}

	@Override
	public Object addBlockReference(String blockName) {
		_helper.addBlockReference(blockName);
		return _returnValue;
	}

	@Override
	public BlockBuilder_addBlockChain startBlock(String blockName) {
		BlockHelper helper = (BlockHelper) _helper.startBlock(blockName);
		BlockBuilder_addBlockChain returnBlock = new ImplBlockBuilder_addBlockChain(helper, this);
		return new ImplBlockBuilder_addBlockChain(helper, returnBlock);
	}
}
