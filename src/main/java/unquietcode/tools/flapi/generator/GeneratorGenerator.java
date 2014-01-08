package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.outline.GeneratorOutline;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorGenerator extends AbstractGenerator {

	public GeneratorGenerator(GeneratorContext context) {
		super(context);
	}

	public JDefinedClass generate(StateClass topLevel, GeneratorOutline outline) {
		JDefinedClass generator = GENERATOR_CLASS_STRATEGY.createType(ctx, topLevel);
		JDefinedClass helper = HELPER_INTERFACE_STRATEGY.createType(ctx, topLevel);

		// FLAPI-126 subclass the return type for consistency between descriptor changes
		JDefinedClass returnType = WRAPPER_INTERFACE_STRATEGY.createType(ctx, topLevel);

		// -- add the constructor method --
		JMethod createMethod = generator.method(JMod.PUBLIC+JMod.STATIC, returnType, outline.methodName);
		JVar pHelper = createMethod.param(helper, "helper");
		JVar pListeners = createMethod.varParam(ExecutionListener.class, "listeners");

		// if (helper == null)
		//     throw new IllegalArgumentException("Helper cannot be null.");
		//
		JConditional _if = createMethod.body()._if(pHelper.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(ref(IllegalArgumentException.class)).arg("Helper cannot be null."));
		createMethod.body().directStatement(" ");

		// BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
		JVar handler = createMethod.body().decl(ref(BlockInvocationHandler.class), "handler",
			JExpr._new(ref(BlockInvocationHandler.class))
				.arg(pHelper)
				.arg(JExpr._null()
			)
		);

		// handler.addListeners(listeners);
		createMethod.body().invoke(handler, "addListeners").arg(pListeners);

		// return handler._proxy(Wrapper.class);
		createMethod.body()._return(
			handler.invoke("_proxy").arg(returnType.dotclass())
		);

		return generator;
	}
}
