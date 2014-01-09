/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.BlockReference;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.concurrent.atomic.AtomicReference;


/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class BlockHelperImpl implements BlockHelper {
	final BlockOutline block;

	public BlockHelperImpl(BlockOutline block) {
		this.block = block;
	}

	@Override
	public void addMethod(String methodSignature, AtomicReference<MethodHelper> _helper1) {
		_addMethod(block, methodSignature, _helper1);
	}

	static void _addMethod(BlockOutline block, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		MethodOutline m = block.addMethod(methodSignature);
		_helper1.set(new MethodHelperImpl(m));
	}

	@Override
	public void startBlock(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2) {
		_startBlock(block, blockName, methodSignature, _helper1, _helper2);
	}

	static void _startBlock(BlockOutline block, String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2) {
		BlockOutline newBlock = block.addBlock(blockName);
		MethodOutline blockMethod = block.addMethod(methodSignature);
		blockMethod.getBlockChain().add(newBlock);
		newBlock.setConstructor(blockMethod);

		_helper1.set(new MethodHelperImpl(blockMethod));
		_helper2.set(new BlockHelperImpl(newBlock));
	}

	@Override
	public void startBlock(String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2) {
		_startBlock(block, null, methodSignature, _helper1, _helper2);
	}

	@Override
	public void addBlockReference(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		_addBlockReference(block, blockName, methodSignature, _helper1);
	}

	static void _addBlockReference(
		BlockOutline block,
		String blockName,
		String methodSignature,
		AtomicReference<MethodHelper> _helper1
	){
		BlockReference blockReference = new BlockReference();
		blockReference.setName(blockName);

		MethodOutline blockMethod = block.addMethod(methodSignature);
		blockMethod.getBlockChain().add(blockReference);
		blockReference.setConstructor(blockMethod);

		_helper1.set(new MethodHelperImpl(blockMethod));
	}

	@Override
	public void addEnumSelector(Class clazz, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		_addEnumSelector(block, clazz, methodSignature, _helper1);
	}

	static void _addEnumSelector(BlockOutline block, Class clazz, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		if (clazz == null) {
			throw new NullPointerException("addEnumSelector: class is null");
		} else  if (!clazz.isEnum()) {
			throw new DescriptorBuilderException("addEnumSelector: class must be an enum class");
		}

		AtomicReference<BlockHelper> blockHelper = new AtomicReference<BlockHelper>();
		_startBlock(block, clazz.getSimpleName(), methodSignature, _helper1, blockHelper);

		for (Object value : clazz.getEnumConstants()) {
			String name = ((Enum) value).name();

			// for every enum value, add a new terminal method
			AtomicReference<MethodHelper> methodHelper = new AtomicReference<MethodHelper>();
			blockHelper.get().addMethod(name+"()", methodHelper);
			methodHelper.get().last();
		}
	}

	@Override
	public void endBlock() {
		// nothing
	}
}
