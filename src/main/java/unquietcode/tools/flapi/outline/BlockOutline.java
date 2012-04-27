package unquietcode.tools.flapi.outline;

import unquietcode.tools.flapi.BlockReference;
import unquietcode.tools.flapi.generator.AbstractGenerator;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	// name of the block
	private String name;

	// nested blocks
	public final List<BlockOutline> blocks = new ArrayList<BlockOutline>();
	
	// constructor, used by parent to create this block
	private MethodOutline constructor;

	public final Set<MethodOutline> methods = new HashSet<MethodOutline>();

	public String getTopLevelImplementation() {
		return "Impl"+ AbstractGenerator.getGeneratedName(name + "Builder", getDynamicMethods());
	}

	public String getTopLevelInterface() {
		return AbstractGenerator.getGeneratedName(name + "Builder", getDynamicMethods());
	}
	
	public String getBaseImplementation() {
		return "Impl"+name+"Builder";
	}
	
	public String getBaseInterface() {
		return name+"Builder";
	}
	
	public String getHelperInterface() {
		return name+"Helper";
	}
	
	public String getGeneratorImplementation() {
		return name+"Generator";
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
		this.name = name;
	}

	public BlockOutline addBlock(String blockName) {
		BlockOutline block = new BlockOutline();
		block.name = blockName;
		blocks.add(block);

		return block;
	}

	public MethodOutline addMethod(String methodSignature) {
		MethodOutline method = new MethodOutline();
		method.methodSignature = methodSignature;
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
}
