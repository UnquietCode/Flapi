/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Supplier;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.outline.GeneratorOutline;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;
import unquietcode.tools.flapi.runtime.Helpers;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorGenerator extends AbstractGenerator {

	public GeneratorGenerator(GeneratorContext context) {
		super(context);
	}

	public JDefinedClass generate(StateClass topLevel, GeneratorOutline outline) {
		JDefinedClass generator = GENERATOR_CLASS_STRATEGY.createStrongType(ctx, topLevel);
		JClass helper = HELPER_INTERFACE_STRATEGY.createWeakType(ctx, topLevel);

		// FLAPI-126 subclass the return type for consistency between descriptor changes
		JClass returnType = WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, topLevel);
		returnType = returnType.narrow(Void.class);

		JMethod createMethod = generator.method(JMod.PUBLIC+JMod.STATIC, returnType, outline.methodName);

		// is this a dynamic helper? use that factory method
		if (topLevel.getBeanClass() != null) {
			createBeanFactoryMethod(helper, topLevel.getBeanClass(), returnType, createMethod);
		}

		// else add the default factory method
		else {
			createDefaultFactoryMethod(helper, returnType, createMethod);
		}

		// also add a static factory method
		JDefinedClass factory = FACTORY_INTERFACE_STRATEGY.createStrongType(ctx, topLevel);
		JMethod factoryInterfaceMethod = factory.method(0, returnType, createMethod.name());
		createFactoryMethod(generator, createMethod, helper, factory, factoryInterfaceMethod);

		return generator;
	}

	private void createBeanFactoryMethod(JClass helper, Class<?> beanType, JClass returnType, JMethod createMethod) {
		JVar pBean = createMethod.param(beanType, "bean");
		JVar pListeners = createMethod.varParam(ExecutionListener.class, "listeners");

		// if (bean == null)
		//     throw new IllegalArgumentException("Bean target cannot be null.");
		//
		JConditional _if = createMethod.body()._if(pBean.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(ref(IllegalArgumentException.class)).arg("Bean target cannot be null."));
		createMethod.body().directStatement(" ");

		JInvocation beanProxyHelper = ref(Helpers.class).staticInvoke("beanProxyHelper")
			.arg(helper.dotclass())
			.arg(pBean)
		;

		JVar proxyHelper = createMethod.body().decl(helper, "helper", beanProxyHelper);
		createMethod.body().directStatement(" ");

		// BlockInvocationHandler handler = new BlockInvocationHandler(helper, bean);
		JVar handler = createMethod.body().decl(ref(BlockInvocationHandler.class), "handler",
			JExpr._new(ref(BlockInvocationHandler.class))
				.arg(proxyHelper)
				.arg(pBean)
		);

		addDefaultMethodBody(pListeners, handler, returnType, createMethod);
	}

	private void createDefaultFactoryMethod(JClass helper, JClass returnType, JMethod createMethod) {
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
				.arg(JExpr._null())
		);


		addDefaultMethodBody(pListeners, handler, returnType, createMethod);
	}

	private void addDefaultMethodBody(JVar pListeners, JVar handler, JClass returnType, JMethod createMethod) {

		// handler.addListeners(listeners);
		createMethod.body().invoke(handler, "addListeners").arg(pListeners);

		// return handler._proxy(Wrapper.class);
		createMethod.body()._return(
		handler.invoke("_proxy").arg(returnType.dotclass())
		);
	}

	private void createFactoryMethod(JDefinedClass generator, JMethod createMethod, JClass helper, JClass factory, JMethod factoryInterfaceMethod) {

		// Supplier<Helper>
		JClass helperSupplier = ref(Supplier.class).narrow(helper);

		// public static Factory factory(Supplier<Helper> provider, ExecutionListener...listeners)
		JMethod factoryMethod = generator.method(JMod.PUBLIC+JMod.STATIC, factory, "factory");
		JVar pHelper = factoryMethod.param(JMod.FINAL, helperSupplier, "provider");
		JVar pListeners = factoryMethod.varParam(JMod.FINAL, ref(ExecutionListener.class), "listeners");

		// return a new anonymous class
		JDefinedClass factoryImplementation = ctx.model.anonymousClass(factory);
		factoryMethod.body()._return(JExpr._new(factoryImplementation));

		// implementation of the anonymous class
		JMethod factoryImplMethod = factoryImplementation.method(JMod.PUBLIC, factoryInterfaceMethod.type(), factoryInterfaceMethod.name());

		// Helper helper = provider.get();
		JVar suppliedHelper = factoryImplMethod.body().decl(helper, "helper", pHelper.invoke("get"));

		// return
		factoryImplMethod.body()._return(

			// Generator.start(helper, listeners)
			generator.staticInvoke(createMethod)
				.arg(suppliedHelper)
				.arg(pListeners)
			)
		;
	}
}
