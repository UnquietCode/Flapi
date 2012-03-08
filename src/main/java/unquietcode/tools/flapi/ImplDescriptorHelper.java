package unquietcode.tools.flapi;

import unquietcode.Pair;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;



/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplDescriptorHelper implements DescriptorHelper {
	final DescriptorData descriptor = new DescriptorData();

	@Override
	public void _setDescriptorName(String name) {
		descriptor.block.blockName = name;
	}

	@Override
	public void _setDescriptorMethod(String method) {
		descriptor.descriptorMethod = method;
	}

	@Override
	public void setPackage(String packageName) {
		descriptor.packageName = packageName;
	}

	@Override
	public void showLog(boolean value) {
		descriptor.showLog = value;
	}

	@Override
	public MethodHelper addMethod(String methodSignature) {
		ImplMethodHelper helper = new ImplMethodHelper();
		MethodData newMethod = helper.method;
		descriptor.block.methods.add(newMethod);
		newMethod.methodSignature = methodSignature;
		
		return helper;
	}

	@Override
	public Pair<MethodHelper,BlockHelper> startBlock(String blockName, String methodSignature) {
		ImplBlockHelper bHelper = new ImplBlockHelper();
		BlockData newBlock = bHelper.block;
		descriptor.block.blocks.add(newBlock);
		newBlock.blockName = blockName;
		newBlock.constructor = new MethodData();
		newBlock.constructor.methodSignature = methodSignature;

		ImplMethodHelper mHelper = new ImplMethodHelper(newBlock.constructor);
		return new Pair<MethodHelper, BlockHelper>(mHelper, bHelper);
	}
}
