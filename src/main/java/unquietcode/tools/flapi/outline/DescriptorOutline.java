package unquietcode.tools.flapi.outline;


import java.util.HashSet;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline implements Outline {
	private final Set<BlockOutline> blocks = new HashSet<BlockOutline>();
	private String packageName;

	public final BlockOutline selfBlock = new BlockOutline();
	private final GeneratorOutline generator = new GeneratorOutline(selfBlock);

	public GeneratorOutline getGenerator() {
		return generator;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setDescriptorName(String name) {
		selfBlock.name = name;
	}
	
	public void setCreateMethod(String methodName) {
		generator.methodName = methodName;
	}
	
	public MethodOutline addMethod(String methodSignature) {
		MethodOutline outline = new MethodOutline();
		outline.methodSignature = methodSignature;
		selfBlock.methods.add(outline);
		return outline;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	
}
