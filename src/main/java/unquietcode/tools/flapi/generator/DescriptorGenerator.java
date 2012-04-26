package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;
import unquietcode.tools.flapi.BlockReference;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorGenerator extends AbstractGenerator<DescriptorOutline, JCodeModel> {
	public DescriptorGenerator(DescriptorOutline outline) {
		super(outline, new GeneratorContext(outline.getPackageName()));
	}
	
	@Override
	public JCodeModel generate() {
		// create the generator
		GeneratorGenerator generatorGen = new GeneratorGenerator(outline.getGenerator(), ctx);
		generatorGen.generate();

		// add the custom methods to the helper interface
		JDefinedClass helper = ctx.getOrCreateInterface(outline.selfBlock.getHelperInterface());
		helper.method(JMod.NONE, ctx.model.VOID, "_setDescriptorName").param(ctx.model._ref(String.class), "name");
		helper.method(JMod.NONE, ctx.model.VOID, "_setDescriptorMethod").param(ctx.model._ref(String.class), "methodName");

		// now process all blocks
		resolveBlockReferences();
		BlockGenerator blockGen = new BlockGenerator(outline.selfBlock, ctx);
		blockGen.generate();

		return ctx.model;
	}

	private void resolveBlockReferences() {
		Map<String, BlockOutline> blocks = new HashMap<String, BlockOutline>();
		_getBlockNames(outline.selfBlock, blocks);
		_resolveBlockReferences(outline.selfBlock, blocks);
	}

	private void _getBlockNames(BlockOutline block, Map<String, BlockOutline> blocks) {
		blocks.put(block.name, block);

		for (BlockOutline child : block.blocks) {
			_getBlockNames(child, blocks);
		}
	}

	private void _resolveBlockReferences(BlockOutline block, Map<String, BlockOutline> blocks) {
		for (MethodOutline method : block.methods) {
			for (BlockOutline aBlock : method.blockChain) {
				if (aBlock instanceof BlockReference) {
					BlockOutline actual = blocks.get(aBlock.name);

					// TODO better error and exception and more details
					if (actual == null) {
						throw new RuntimeException("Invalid block reference '"+aBlock.name+"'.");
					}

					// at least set the methods
					aBlock.methods.addAll(actual.methods);
				}
			}
		}

		// TODO probably phase out explicit references list

		for (BlockOutline child : block.blocks) {
			_resolveBlockReferences(child, blocks);
		}
	}
}
