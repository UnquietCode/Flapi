package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
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
		final BlockOutline block = outline;

		BlockGenerator_Helper helperGen = new BlockGenerator_Helper(outline, ctx);
		JDefinedClass iHelper = helperGen.generate();

		BlockGenerator_BaseInterface baseInterfaceGen = new BlockGenerator_BaseInterface(outline, ctx);
		JDefinedClass iBuilder = baseInterfaceGen.generate();

		BlockGenerator_BaseImplementation baseImplementationGen = new BlockGenerator_BaseImplementation(outline, ctx);
		JDefinedClass cBuilder = baseImplementationGen.generate();

		BlockGenerator_Subsets subsetsGen = new BlockGenerator_Subsets(outline, ctx);
		subsetsGen.generate();

		for (BlockOutline child : block.blocks) {
			BlockGenerator childGenerator = new BlockGenerator(child, ctx);
			childGenerator.generate();

			// if child already exists, then this is an error!
			// TODO throw exception
			// can't we just assume that the collision check would have caught this?
		}

		return null;

	}
}
