package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public interface BlockChainBuilder<_ReturnType> {
	_ReturnType addBlockReference(String blockName);
	BlockBuilder_addBlockChain<_ReturnType> startBlock(String blockName);
}
