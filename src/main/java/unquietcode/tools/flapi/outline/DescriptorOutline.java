package unquietcode.tools.flapi.outline;



/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline implements Outline {
	private String packageName;

	public final BlockOutline selfBlock = new BlockOutline();
	private final GeneratorOutline generator = new GeneratorOutline(selfBlock);

	public GeneratorOutline getGenerator() {
		return generator;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public String getDescriptorName() {
		return selfBlock.getName();
	}

	public void setDescriptorName(String name) {
		selfBlock.setName(name);
	}

	public void setReturnType(Class returnType) {
		generator.returnType = returnType;
	}

	public Class getReturnType() {
		return generator.returnType;
	}

	public void setCreateMethod(String methodName) {
		generator.methodName = methodName;
	}
	
	public MethodOutline addMethod(String methodSignature) {
		return selfBlock.addMethod(methodSignature);
	}

	public BlockOutline addBlock(String blockName) {
		return selfBlock.addBlock(blockName);
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
