package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockBuilder_addBlockChain<_ReturnType> extends BlockBuilder<BlockBuilder_addBlockChain<_ReturnType>, _ReturnType> {
	BlockChainBuilder_addBlockChain<BlockBuilder<BlockBuilder<_ReturnType>, _ReturnType>> addBlockChain();
}
