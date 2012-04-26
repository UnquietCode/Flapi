package unquietcode.tools.flapi.builder;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface BlockHelper {
	List<Object> addMethod(String methodSignature);
	List<Object> startBlock(String blockName, String methodSignature);
	List<Object> addBlockReference(String blockName, String methodSignature);
	List<Object> addBlockChain();

	void endBlock();
}
