package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class DescriptorHelperImpl implements DescriptorHelper {
	final DescriptorOutline outline = new DescriptorOutline();

	@Override
	public Descriptor _getReturnValue() {
		return new Descriptor(this);
	}

	@Override
	public void setDescriptorName(String descriptorName) {
		// nothing
	}

	@Override
	public void setReturnType(Class returnType) {
		// nothing
	}

	@Override
	public void setStartingMethodName(String methodName) {
		// nothing
	}

	@Override
	public void build() {
		// nothing
	}

	public void _setDescriptorName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		
		outline.setDescriptorName(name);
	}

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
	public void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		BlockHelperImpl._addMethod(outline.selfBlock, methodSignature, _helper1);
	}

	@Override
	public void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline.selfBlock, blockName, methodSignature, _helper1, _helper2);
	}
}
