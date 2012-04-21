package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockChainBuilder_addBlockChain<_ReturnType> extends BlockChainBuilder<BlockChainBuilder_addBlockChain<_ReturnType>> {
	BlockChainBuilder<_ReturnType> addBlockChain(String blockName);
}
