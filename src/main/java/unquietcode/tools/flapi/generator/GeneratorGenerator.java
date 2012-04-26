package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.outline.GeneratorOutline;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorGenerator extends AbstractGenerator<GeneratorOutline, JDefinedClass> {

	public GeneratorGenerator(GeneratorOutline outline, GeneratorContext context) {
		super(outline, context);

		// TODO temporary hack!
		outline.returnType = Descriptor.class;
		outline.methodName = "create";
		outline.descriptorBlock.name = "Descriptor";
	}

	@Override
	public JDefinedClass generate() {
		JType returnType = getInterface(outline.descriptorBlock.getTopLevelInterface()).narrow(outline.returnType);
		JDefinedClass returnValue = getClass(outline.descriptorBlock.getTopLevelImplementation());
		JDefinedClass generator = getClass(outline.descriptorBlock.getGeneratorImplementation());
		JDefinedClass helper = getInterface(outline.descriptorBlock.getHelperInterface());

		// -- add the constructor methods --

		JMethod createMethod = generator.method(JMod.PUBLIC+JMod.STATIC, returnType, outline.methodName);
		createMethod.annotate(SuppressWarnings.class).param("value", "unchecked");
		JVar pHelper = createMethod.param(helper, "helper");

		// check arguments

		// if (helper == null)
		//      throw new IllegalArgumentException("Helper cannot be null.");
		//
		JConditional _if = createMethod.body()._if(pHelper.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(ref(DescriptorBuilderException.class)).arg("Helper cannot be null."));
		createMethod.body().directStatement(" ");

		// get base return value and return
		createMethod.body()._return(
			JExpr._new(returnValue)
				.arg(pHelper)
				.arg(pHelper.invoke("_getReturnValue")
		));

		return generator;
	}
}
