package unquietcode.tools.flapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/
public class BlockData {
	String blockName;
	MethodData constructor;
	BlockData parent;

	final List<MethodData> methods = new ArrayList<MethodData>();
	final List<BlockData> blocks = new ArrayList<BlockData>();
	final List<BlockReference> blockReferences = new ArrayList<BlockReference>();

	@Deprecated
	void _addBlock(BlockData block) {
		blocks.add(block);
	}

	@Deprecated
	void _addMethod(MethodData method) {
		methods.add(method);
	}
	
	static class BlockReference {
		String blockName;
		String constructorMethod;
	}
}
