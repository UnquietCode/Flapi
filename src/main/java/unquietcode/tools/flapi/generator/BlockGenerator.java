package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-11-2012
 */
public class BlockGenerator extends AbstractGenerator<BlockOutline, Void> {

	public BlockGenerator(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public Void generate() {
		JDefinedClass iHelper = generateHelper();

		BlockGenerator_Subsets subsetsGen = new BlockGenerator_Subsets(outline, ctx);
		subsetsGen.generate();

		for (BlockOutline child : outline.blocks) {
			BlockGenerator childGenerator = new BlockGenerator(child, ctx);
			childGenerator.generate();

			// if child already exists, then this is an error!
			// TODO throw exception
			// can't we just assume that the collision check would have caught this?
		}

		return null;

	}

	public JDefinedClass generateHelper() {
		JDefinedClass iHelper = getHelperInterface(outline);

		for (MethodOutline method : outline.methods) {
			JMethod _method = addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);

			// for every block in the chain, add a wrapped helper parameter
			int i=1;
			for (BlockOutline block : method.getBlockChain()) {
				JDefinedClass blockHelper = getHelperInterface(block);
				_method.param(ref(ObjectWrapper.class).narrow(blockHelper), "_helper"+(i++));
			}
		}

		return iHelper;
	}
}
