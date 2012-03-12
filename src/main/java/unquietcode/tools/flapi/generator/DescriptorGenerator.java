package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JCodeModel;
import unquietcode.tools.flapi.outline.DescriptorOutline;

/**
 * @author Ben Fagin (Nokia)
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

		// now process all blocks
		BlockGenerator blockGen = new BlockGenerator(outline.selfBlock, ctx);
		blockGen.generate();

		return ctx.model;
	}
}
