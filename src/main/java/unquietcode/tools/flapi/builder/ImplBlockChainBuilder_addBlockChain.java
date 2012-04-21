package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockChainBuilder_addBlockChain<_ReturnType> implements BlockChainBuilder_addBlockChain<_ReturnType> {
	private final BlockChainHelper _helper;
	private final _ReturnType _returnValue;

	ImplBlockChainBuilder_addBlockChain(BlockChainHelper helper, _ReturnType returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}

	@Override
	public _ReturnType addBlockReference(String blockName) {
		return null;
	}

	@Override
	public BlockBuilder_addBlockChain<_ReturnType> startBlock(String blockName) {
		return null;
	}

	@Override
	public BlockChainBuilder<_ReturnType> addBlockChain(String blockName) {
		return null;
	}
}
