package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import unquietcode.tools.flapi.BlockReference;
import unquietcode.tools.flapi.DescriptorBuilderException;
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
		ctx.condenseNames(outline.shouldEnableCondensedNames());
	}
	
	@Override
	public JCodeModel generate() {
		// create the generator
		GeneratorGenerator generatorGen = new GeneratorGenerator(outline.getGenerator(), ctx);
		generatorGen.generate();

		// add the custom methods to the helper interface
		JDefinedClass helper = getHelperInterface(outline.selfBlock);
		helper.method(JMod.NONE, ref(outline.getReturnType()), "_getReturnValue");

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
		blocks.put(block.getName(), block);

		for (BlockOutline child : block.blocks) {
			_getBlockNames(child, blocks);
		}
	}

	private void _resolveBlockReferences(BlockOutline block, Map<String, BlockOutline> blocks) {
		for (MethodOutline method : block.methods) {
			for (final BlockOutline aBlock : method.getBlockChain()) {
				if (aBlock instanceof BlockReference) {
					final BlockOutline actual = blocks.get(aBlock.getName());

					if (actual == null) {
						StringBuilder sb = new StringBuilder();
						sb.append("Invalid block reference '").append(aBlock.getName()).append("'.\n")
						  .append("Referenced in method ").append(method.methodSignature)
						  .append(" of block '").append(block.getName()).append("'.");

						throw new DescriptorBuilderException(sb.toString());
					}

					// set the methods
					aBlock.methods.addAll(actual.methods);
				}
			}
		}

		for (BlockOutline child : block.blocks) {
			_resolveBlockReferences(child, blocks);
		}
	}
}
