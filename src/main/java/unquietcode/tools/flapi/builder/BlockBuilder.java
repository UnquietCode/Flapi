package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface BlockBuilder<_ReturnType> {
	MethodBuilder_addBlockChain<BlockBuilder<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<BlockBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);
	MethodBuilder_addBlockChain<BlockBuilder<_ReturnType>> addBlockReference(String blockName, String methodSignature);
	_ReturnType endBlock();
}
