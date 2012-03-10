package unquietcode.tools.flapi.outline;


import java.util.HashSet;
import java.util.Set;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class DescriptorOutline {
	final Set<BlockOutline> blocks = new HashSet<BlockOutline>();
	String name;
	String packageName;
	final GeneratorOutline generator = new GeneratorOutline();
	final BlockOutline selfBlock = new BlockOutline();
	
	
	
	public void setDescriptorName(String name) {
		selfBlock.name = name;
	}
	
	public void setCreateMethod(String methodName) {
		generator.methodName = methodName;
	}
	
	public void addMethod(String methodSignature) {
		MethodOutline outline = new MethodOutline();
		outline.methodSignature = methodSignature;
		selfBlock.methods.add(outline);
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	
}
