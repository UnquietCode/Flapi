package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

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
		BlockGenerator blockGen = new BlockGenerator(outline.selfBlock, ctx);
		blockGen.generate();

		return ctx.model;
	}

	private void addRequiredMethod(String methodName) {

	}
}
