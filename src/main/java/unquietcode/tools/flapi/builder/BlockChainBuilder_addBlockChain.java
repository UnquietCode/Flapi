package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public interface BlockChainBuilder_addBlockChain<_ReturnType> extends BlockChainBuilder<BlockChainBuilder_addBlockChain, _ReturnType> {
	BlockChainBuilder_addBlockChain<BlockChainBuilder_addBlockChain<_ReturnType>> addBlockChain(String blockName);
}
