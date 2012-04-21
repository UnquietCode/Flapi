package unquietcode.tools.flapi.builder;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 04-21-2012
 */
public interface BlockChainHelper {
	void addBlockReference(String blockName);
	BlockHelper startBlock(String blockName);
	BlockChainHelper addBlockChain();
}
