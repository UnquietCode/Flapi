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
		return selfBlock.name;
	}

	public void setDescriptorName(String name) {
		selfBlock.name = name;
	}

	public void setReturnType(Class returnType) {
		generator.returnType = returnType;
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
