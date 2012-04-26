package unquietcode.tools.flapi.builder;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockChainHelper {
	void addBlockReference(String blockName);
	List<Object> startBlock(String blockName);
	List<Object> addBlockChain();
}
