package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.MethodHelper;


/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplBlockHelper implements BlockHelper {
	final BlockData block = new BlockData();

	@Override
	public MethodHelper _getConstructor() {
		if (block.constructor == null) {
			block.constructor = new MethodData();
		}

		return new ImplMethodHelper(block.constructor);
	}

	@Override
	public MethodHelper addMethod(String methodSignature) {
		ImplMethodHelper helper = new ImplMethodHelper();
		MethodData newMethod = helper.method;
		block.methods.add(newMethod);
		newMethod.methodSignature = methodSignature;
		
		return helper;
	}

	@Override
	public BlockHelper startBlock(String blockName, String methodSignature) {
		ImplBlockHelper helper = new ImplBlockHelper();
		BlockData newBlock = helper.block;
		block.blocks.add(newBlock);
		newBlock.blockName = blockName;
		newBlock.constructor = new MethodData();
		newBlock.constructor.methodSignature = methodSignature;
				
		return helper;
	}

	@Override
	public MethodHelper addBlockReference(String blockName, String methodSignature) {
		ImplMethodHelper helper = new ImplMethodHelper();
		BlockData.BlockReference blockReference = new BlockData.BlockReference();
		block.blockReferences.add(blockReference);
		blockReference.blockName = blockName;
		blockReference.constructorMethod = methodSignature;

		return helper;
	}

	@Override
	public void endBlock() {
		// TODO
	}

	@Override
	public void endBlock(String methodSignature) {
		// TODO
	}
}
