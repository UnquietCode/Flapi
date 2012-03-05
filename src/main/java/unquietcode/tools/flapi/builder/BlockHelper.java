package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface BlockHelper {
	MethodHelper _getConstructor();
	
	MethodHelper addMethod(String methodSignature);
	BlockHelper startBlock(String blockName, String methodSignature);
	MethodHelper addBlockReference(String blockName, String methodSignature);

	void endBlock();
	void endBlock(String methodSignature);
}
