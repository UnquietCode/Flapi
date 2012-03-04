package unquietcode.tools.flapi;

import java.util.ArrayList;
import java.util.List;

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

	void _addBlock(BlockData block) {
		blocks.add(block);
	}

	void _addMethod(MethodData method) {
		methods.add(method);
	}
}
