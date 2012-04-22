package unquietcode.tools.flapi;

import unquietcode.Pair;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
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
	public List<Object> addMethod(String methodSignature) {
		MethodOutline m = outline.addMethod(methodSignature);
		ArrayList<Object> helpers = new ArrayList<Object>();
		helpers.add(new MethodHelperImpl(m));
		return helpers;
	}

	@Override
	public List<Object> startBlock(String blockName, String methodSignature) {
		BlockOutline block = outline.addBlock(blockName);
		MethodOutline blockMethod = outline.addMethod(methodSignature);
		blockMethod.blockChain.add(0, block);
		block.constructor = blockMethod;

		List<Object> helpers = new ArrayList<Object>();
		helpers.add(new MethodHelperImpl(blockMethod));
		helpers.add(new BlockHelperImpl(block));

		return helpers;
	}
}
