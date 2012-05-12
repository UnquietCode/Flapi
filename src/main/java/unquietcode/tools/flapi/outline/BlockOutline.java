package unquietcode.tools.flapi.outline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	// name of the block
	private String name;

	// nested blocks
	private final List<BlockOutline> blocks = new ArrayList<BlockOutline>();

	// constructor, used by parent to create this block
	private MethodOutline constructor;

	// block methods
	private final Set<MethodOutline> methods = new HashSet<MethodOutline>();

	// ------------------------------ //

	public List<BlockOutline> getBlocks() {
		return Collections.unmodifiableList(blocks);
	}

	public MethodOutline getConstructor() {
		return constructor;
	}

	public void setConstructor(MethodOutline constructor) {
		this.constructor = constructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public BlockOutline addBlock(String blockName) {
		BlockOutline block = new BlockOutline();
		block.name = blockName;
		blocks.add(block);

		return block;
	}

	public MethodOutline addMethod(String methodSignature) {
		MethodOutline method = new MethodOutline();
		method.setMethodSignature(methodSignature);
		methods.add(method);

		return method;
	}

	public Set<MethodOutline> getRequiredMethods() {
		Set<MethodOutline> required = new TreeSet<MethodOutline>();
		
		for (MethodOutline method : methods) {
			if (method.isRequired()) {
				required.add(method);
			}
		}
		
		return required;
	}

	public Set<MethodOutline> getDynamicMethods() {
		Set<MethodOutline> dynamic = new TreeSet<MethodOutline>();

		for (MethodOutline method : methods) {
			if (!method.isRequired()) {
				dynamic.add(method);
			}
		}

		return dynamic;
	}

	public Set<MethodOutline> getAllMethods() {
		return new HashSet<MethodOutline>(methods);
	}
}
