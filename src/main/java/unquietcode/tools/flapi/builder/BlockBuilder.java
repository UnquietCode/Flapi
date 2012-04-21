package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface BlockBuilder<_ReturnType> {
	MethodBuilder<BlockBuilder<_ReturnType>> addMethod(String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<BlockBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);
	MethodBuilder<BlockBuilder<_ReturnType>> addBlockReference(String blockName, String methodSignature);
	_ReturnType endBlock();
}
