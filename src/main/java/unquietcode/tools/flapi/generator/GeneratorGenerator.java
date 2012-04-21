package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.outline.GeneratorOutline;


/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class GeneratorGenerator extends AbstractGenerator<GeneratorOutline, JDefinedClass> {

	public GeneratorGenerator(GeneratorOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public JDefinedClass generate() {

		JDefinedClass iTop = getInterface(outline.descriptorBlock.getTopLevelInterface());
		JDefinedClass generator = getClass(outline.descriptorBlock.getGeneratorImplmentation());
		JDefinedClass helper = getInterface(outline.descriptorBlock.getHelperInterface());
		JDefinedClass rType = getInterface(outline.descriptorBlock.getTopLevelImplementation());
		
		// -- add the constructor methods --

		JMethod createWithString = generator.method(JMod.PUBLIC+JMod.STATIC, iTop, outline.methodName);
		JVar pName = createWithString.param(ref(String.class), "name");
		JVar pMethod = createWithString.param(ref(String.class), "method");
		JVar pHelper = createWithString.param(helper, "helper");

		// check arguments

		// if (method == null || method.trim().isEmpty())
		//      throw new IllegalArgumentException("Name cannot be empty.");
		// else
		//      method = method.trim();
		JConditional _if = createWithString.body()
				._if(pMethod.eq(JExpr._null()).cor(pMethod.invoke("trim").invoke("isEmpty")));
		_if._then()._throw(JExpr._new(ref(IllegalArgumentException.class)).arg("Name cannot be empty."));
		_if._else().assign(pMethod, pMethod.invoke("trim"));
		createWithString.body().directStatement(" ");

		// if (helper == null)
		//      throw new IllegalArgumentException("Helper cannot be null.");
		_if = createWithString.body()._if(pHelper.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(ref(IllegalArgumentException.class)).arg("Helper cannot be null."));
		createWithString.body().directStatement(" ");

		// set and return
		createWithString.body().invoke(pHelper, "_setDescriptorName").arg(pName);
		createWithString.body().invoke(pHelper, "_setDescriptorMethod").arg(pMethod);
		createWithString.body()._return(JExpr._new(rType).arg(pHelper));

		// create the second constructor, which uses the default string
		JMethod create = generator.method(JMod.PUBLIC+JMod.STATIC, iTop, outline.methodName);
		pName = create.param(ref(String.class), "name");
		pHelper = create.param(helper, "helper");

		create.body()._return(JExpr.invoke(createWithString).arg(pName).arg("create").arg(pHelper));

		return generator;
	}
}
