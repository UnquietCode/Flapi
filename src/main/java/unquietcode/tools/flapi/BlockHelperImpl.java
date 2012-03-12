package unquietcode.tools.flapi;

import unquietcode.Pair;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;


/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class BlockHelperImpl implements BlockHelper {
	final BlockOutline block;

	public BlockHelperImpl(BlockOutline block) {
		this.block = block;
	}
	
	@Override
	public MethodHelper addMethod(String methodSignature) {
		MethodHelperImpl helper = new MethodHelperImpl();
		MethodOutline newMethod = helper.method;
		block.methods.add(newMethod);
		newMethod.methodSignature = methodSignature;
		
		return helper;
	}

	@Override
	public Pair<MethodHelper,BlockHelper> startBlock(String blockName, String methodSignature) {
		BlockOutline newBlock = new BlockOutline();
		block.blocks.add(newBlock);
		newBlock.name = blockName;
		newBlock.constructor = new MethodOutline();
		newBlock.constructor.methodSignature = methodSignature;

		MethodHelperImpl mHelper = new MethodHelperImpl(newBlock.constructor);
		return new Pair<MethodHelper, BlockHelper>(mHelper, new BlockHelperImpl(newBlock));
	}

	@Override
	public MethodHelper addBlockReference(String blockName, String methodSignature) {
		MethodHelperImpl helper = new MethodHelperImpl();
		BlockReference blockReference = new BlockReference();
		block.blockReferences.add(blockReference);
		blockReference.blockName = blockName;
		blockReference.constructorMethod = methodSignature;

		return helper;
	}

	@Override
	public void endBlock() {
		// TODO
	}
}
