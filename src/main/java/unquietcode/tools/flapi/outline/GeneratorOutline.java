package unquietcode.tools.flapi.outline;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorOutline implements Outline {
	public String methodName = "create";
	public BlockOutline descriptorBlock;
	
	public GeneratorOutline(BlockOutline descriptorBlock) {
		this.descriptorBlock = descriptorBlock;
	}
}
