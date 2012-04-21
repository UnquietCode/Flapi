package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockBuilder_addBlockChain<_ReturnType> {
	MethodBuilder<BlockBuilder_addBlockChain<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>>> startBlock(String blockName, String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<_ReturnType>> addBlockReference(String blockName, String methodSignature);
	_ReturnType endBlock();

	BlockChainBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>> addBlockChain();
}
