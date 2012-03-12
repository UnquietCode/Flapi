package unquietcode.tools.flapi.builder;

import unquietcode.Pair;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface BlockHelper {
	MethodHelper addMethod(String methodSignature);
	Pair<MethodHelper,BlockHelper> startBlock(String blockName, String methodSignature);
	MethodHelper addBlockReference(String blockName, String methodSignature);

	void endBlock();
}
