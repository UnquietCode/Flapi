package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public class ImplBlockChainBuilder<_ReturnType> implements BlockChainBuilder<_ReturnType> {
	@Override
	public _ReturnType addBlockReference(String blockName) {
		return null;
	}

	@Override
	public BlockBuilder<_ReturnType> startBlock(String blockName) {
		return null;
	}
}
