package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockChainBuilder_addBlockChain<_ReturnType> {
	_ReturnType addBlockReference(String blockName);
	BlockBuilder_addBlockChain<_ReturnType> startBlock(String blockName);

	BlockChainBuilder<_ReturnType> addBlockChain(String blockName);
}
