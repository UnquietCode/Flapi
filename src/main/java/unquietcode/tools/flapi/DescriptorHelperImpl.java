package unquietcode.tools.flapi;

import unquietcode.Pair;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-10-2012
 */
public class DescriptorHelperImpl implements DescriptorHelper {
	final DescriptorOutline outline = new DescriptorOutline();
	
	
	
	
	@Override
	public void _setDescriptorName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		
		outline.setDescriptorName(name);
	}

	@Override
	public void _setDescriptorMethod(String method) {
		outline.setCreateMethod(method);
	}

	@Override
	public void setPackage(String packageName) {
		outline.setPackageName(packageName);
	}

	@Override
	public void showLog(boolean value) {
		// TODO
	}

	@Override
	public MethodHelper addMethod(String methodSignature) {
		MethodOutline m = outline.addMethod(methodSignature);
		MethodHelper helper = new MethodHelperImpl(m);
		return helper;
	}

	@Override
	public Pair<MethodHelper, BlockHelper> startBlock(String blockName, String methodSignature) {
		BlockOutline block = new BlockOutline();
		block.name = blockName;
		block.constructor = new MethodOutline();
		block.constructor.methodSignature = methodSignature;
		return new Pair<MethodHelper, BlockHelper>(new MethodHelperImpl(block.constructor), new BlockHelperImpl(block));
	}
}
