package unquietcode.tools.flapi;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/ // block
public interface BlockInterface<_ReturnType> {
	MethodInterface<BlockInterface<_ReturnType>> startBlock(String blockName, String methodSignature);
	MethodInterface<_ReturnType> addBlockReference(String blockName, String methodSignature);
	MethodInterface<_ReturnType> addMethod(String methodSignature);
	_ReturnType endBlock();
	_ReturnType endBlock(String methodSignature);
}
