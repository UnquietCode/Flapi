package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface BlockBuilder<_SelfType extends BlockBuilder<_SelfType, _ReturnType>, _ReturnType> {
	MethodBuilder<_SelfType> addMethod(String methodSignature);
	MethodBuilder<BlockBuilder_addBlockChain<_SelfType>> startBlock(String blockName, String methodSignature);
	MethodBuilder<_SelfType> addBlockReference(String blockName, String methodSignature);
	_ReturnType endBlock();
}
