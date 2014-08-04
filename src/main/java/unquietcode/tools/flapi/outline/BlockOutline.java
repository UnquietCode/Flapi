/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.outline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	private String name;
	private String returnType;
	private Class<?> helperClass;

	// nested blocks
	private final List<BlockOutline> blocks = new ArrayList<BlockOutline>();

	// constructor, used by parent to create this block
	private MethodOutline constructor;

	// block methods
	private final Set<MethodOutline> methods = new HashSet<MethodOutline>();

	// ------------------------------ //

	public Class<?> getHelperClass() {
		return helperClass;
	}

	public void setHelperClass(Class<?> helperClass) {
		this.helperClass = helperClass;
	}

	public String getReturnType() {
		return constructor != null ? constructor.getReturnType() : returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

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

	public Set<MethodOutline> getTriggeredMethods() {
		Set<MethodOutline> triggered = new TreeSet<MethodOutline>();

		for (MethodOutline method : methods) {
			if (!method.isRequired() && method.getTrigger() != null) {
				triggered.add(method);
			}
		}

		return triggered;
	}

	public Set<MethodOutline> getAllMethods() {
		return methods;
	}
}
