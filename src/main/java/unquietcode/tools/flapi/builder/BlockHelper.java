package unquietcode.tools.flapi.builder;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface BlockHelper {
	MethodHelper addMethod(String methodSignature);
	List<Object> startBlock(String blockName, String methodSignature);
	MethodHelper addBlockReference(String blockName, String methodSignature);

	BlockChainHelper addBlockChain();

	void endBlock();
}
