/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

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
