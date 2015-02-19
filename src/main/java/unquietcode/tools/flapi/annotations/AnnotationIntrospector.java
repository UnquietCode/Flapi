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

package unquietcode.tools.flapi.annotations;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.IntrospectorSupport;
import unquietcode.tools.flapi.beans.BeanIntrospector;
import unquietcode.tools.flapi.builder.Annotation.AnnotationHelper;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.helpers.BlockHelperImpl;
import unquietcode.tools.flapi.helpers.MethodHelperImpl;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.runtime.Consumer;
import unquietcode.tools.flapi.runtime.EnumSelectorHint;
import unquietcode.tools.flapi.runtime.Helpers;
import unquietcode.tools.flapi.runtime.SpringMethodUtils;
import unquietcode.tools.spring.generics.MethodParameter;
import unquietcode.tools.spring.generics.ResolvableType;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 2014-08-03
 */
public class AnnotationIntrospector extends IntrospectorSupport {
	private final Map<Class<?>, BlockOutline> blocks = new HashMap<Class<?>, BlockOutline>();

	public static boolean isAnnotated(Class<?> clazz) {

		// for every method in the class
		for (Method method : getAllMethods(clazz)) {
			List<Annotation> methodQuantifiers = findAnnotatedElements(MethodQuantifier.class, method.getAnnotations());

			// if the method has at least one marker
			// then it is annotated (even though having
			// more than one marker is technically an
			// error, the intent is still clear)
			if (methodQuantifiers.size() > 0) {
				return true;
			}
		}

		// we didn't find any methods
		return false;
	}

	private static Set<Method> getAllMethods(Class<?> clazz) {
		Method[] methods = SpringMethodUtils.getUniqueDeclaredMethods(clazz);
		return new HashSet<>(Arrays.asList(methods));
	}

	public static DescriptorOutline createDescriptor(Class<?> clazz) {
		DescriptorOutline descriptor = new DescriptorOutline();
		descriptor.setPackageName(clazz.getPackage().getName() + ".builder");

		// discover methods and set them on the blocks
		AnnotationIntrospector introspector = new AnnotationIntrospector();
		boolean found = introspector.handleClass(descriptor, clazz);

		if (found) {
			return descriptor;
		}

		// if we didn't find any, try the bean introspector
		BeanIntrospector beanIntrospector = new BeanIntrospector();
		descriptor = beanIntrospector.createDescriptor(clazz);
		return descriptor;
	}

	public static BlockOutline createBlock(Class<?> clazz) {
		return createDescriptor(clazz);
	}

	private BlockOutline handleClass(Class<?> blockClass) {
		if (blocks.containsKey(blockClass)) {
			return blocks.get(blockClass);
		}

		BlockOutline outline = new BlockOutline();
		blocks.put(blockClass, outline);
		handleClass(outline, blockClass);

		return outline;
	}

	private boolean handleClass(BlockOutline blockOutline, Class<?> blockClass) {
		BlockHelper helper = new BlockHelperImpl(blockOutline);
		Block block = blockClass.getAnnotation(Block.class);
		blockOutline.setHelperClass(blockClass);

		// block name
		if (block != null && !block.name().trim().isEmpty()) {
			blockOutline.setName(block.name());
		} else {
			blockOutline.setName(blockClass.getSimpleName());
		}

		boolean atLeastOne = false;

		for (Method method : getAllMethods(blockClass)) {
			List<Annotation> methodQuantifiers = findAnnotatedElements(MethodQuantifier.class, method.getAnnotations());

			// skip methods which are not annotated
			if (methodQuantifiers.size() == 0) {
				continue;
			}

			// only allow one quantifier annotation per method
			if (methodQuantifiers.size() > 1) {
				throw new DescriptorBuilderException(
					"Only one annotation from " +
					"{@Any, @AtLeast, @AtMost, @Between, @Exactly, @Last}" +
					" is allowed per method."
				);
			}

			handleMethod(helper, method);
			atLeastOne = true;
		}

		return atLeastOne;
	}

	private void handleMethod(final BlockHelper blockHelper, final Method method) {
		final After after = method.getAnnotation(After.class);
		final Any any = method.getAnnotation(Any.class);
		final AtLeast atLeast = method.getAnnotation(AtLeast.class);
		final AtMost atMost = method.getAnnotation(AtMost.class);
		final Between between = method.getAnnotation(Between.class);
		final Documented documented = method.getAnnotation(Documented.class);
		final Exactly exactly = method.getAnnotation(Exactly.class);
		final Last last = method.getAnnotation(Last.class);
		final EnumSelector selector = method.getAnnotation(EnumSelector.class);

		final String methodSignature = getMethodSignature(method);
		final MethodHelper methodHelper;

		// is it an enum selector?
		if (selector != null) {
			final Class<?>[] parameterTypes = method.getParameterTypes();

			// check parameter length
			if (parameterTypes.length != 0) {
				throw new DescriptorBuilderException("@EnumSelector methods must have zero arguments");
			}

			// check return type
			if (!Consumer.class.isAssignableFrom(method.getReturnType())) {
				throw new DescriptorBuilderException("@EnumSelector methods must return Consumer<EnumType>");
			}

			final Class<?> genericEnum = ResolvableType.forMethodReturnType(method).resolveGeneric(0);

			// check consumer enum type
			if (!genericEnum.isEnum()) {
				throw new DescriptorBuilderException("@EnumSelector methods must return a consumer of Enum types");
			}

			methodHelper = Helpers.invoke(new Helpers.Invoker<MethodHelper>() {
				public void call(AtomicReference<MethodHelper> _helper) {
					blockHelper.addEnumSelector(genericEnum, methodSignature, _helper);
				}
			});

			AnnotationHelper annotationHelper = Helpers.invoke(new Helpers.Invoker<AnnotationHelper>() {
				public void call(AtomicReference<AnnotationHelper> _helper) {
					methodHelper.addAnnotation(EnumSelectorHint.class, _helper);
				}
			});

			annotationHelper.withParameter("value", genericEnum);
			annotationHelper.finish();
		}

		// regular method
		else {
			methodHelper = Helpers.invoke(new Helpers.Invoker<MethodHelper>() {
				public void call(AtomicReference<MethodHelper> _helper) {
					blockHelper.addMethod(methodSignature, _helper);
				}
			});
		}

		// TODO meh
		final MethodOutline methodOutline = ((MethodHelperImpl) methodHelper).getOutline();


		// @After
		if (after != null) {
			methodHelper.after(after.value());
		}

		// @Any
		if (any != null) {
			if (any.group() == Constants.DEFAULT_NULL_INT) {
				methodHelper.any();
			} else {
				methodHelper.any(any.group());
			}
		}

		// @AtLeast
		if (atLeast != null) {
			methodHelper.atLeast(atLeast.value());
		}

		// @AtMost
		if (atMost != null) {
			if (atMost.group() == Constants.DEFAULT_NULL_INT) {
				methodHelper.atMost(atMost.value());
			} else {
				methodHelper.atMost(atMost.value(), atMost.group());
			}
		}

		// @Between
		if (between != null) {
			methodHelper.between(between.minInc(), between.maxInc());
		}

		// @Exactly
		if (exactly != null) {
			methodHelper.exactly(exactly.value());
		}

		// @Last
		if (last != null) {
			Class<?> returnType = method.getReturnType();

			if (returnType == void.class) {
				methodHelper.last();
			} else {
				Class<?>[] generics = ResolvableType.forMethodReturnType(method).resolveGenerics();
				methodHelper.last(makeTypeWithGenerics(returnType, generics));
			}
		}

		// ensure that non-terminal methods aren't returning anything
		else if (selector == null) {
			Class<?> returnType = method.getReturnType();

			if (returnType != void.class) {
				throw new DescriptorBuilderException(
					"Only @Last methods can return anything; all other methods must be 'void'.\n\t" +
					"(for method '"+method.getDeclaringClass().getName()+"#"+method.getName()+"')\n"
				);
			}
		}

		// @Documented
		if (documented != null) {
			DocumentationHelper docHelper = Helpers.invoke(new Helpers.Invoker<DocumentationHelper>() {
				public void call(AtomicReference<DocumentationHelper> _helper) {
					methodHelper.withDocumentation(_helper);
				}
			});

			for (String docString : documented.value()) {
				docHelper.addContent(docString);
			}

			docHelper.finish();
		}

		// other annotations
		for (Annotation annotation : method.getAnnotations()) {

			// skip over Flapi's own annotation types
			if (annotation.annotationType().getAnnotation(FlapiAnnotation.class) != null) {
				continue;
			}
			handleMethodAnnotation(methodHelper, annotation);
		}

		// block chaining
		for (int i=0; i < method.getParameterTypes().length; ++i) {
			Class<?> parameterType = method.getParameterTypes()[i];
			BlockChain blockChain = getParameterAnnotation(method, i, BlockChain.class);

			if (blockChain != null) {

				// check type
				if (parameterType != AtomicReference.class) {
					throw new DescriptorBuilderException("@BlockChain parameters must be of type AtomicReference");
				}

				// get the generic type of the reference
				Class<?> generic = ResolvableType.forMethodParameter(MethodParameter.forMethodOrConstructor(method, i)).resolveGeneric();

				// handle the reference type
				BlockOutline blockOutline = handleClass(generic);
				methodOutline.getBlockChain().add(blockOutline);
				methodOutline.getChainParameterPositions().add(i);
			}
		}
	}
	
	private static void handleMethodAnnotation(final MethodHelper methodHelper, Annotation annotation) {
		final Class<? extends Annotation> annotationClass = annotation.annotationType();

		AnnotationHelper annotationHelper = Helpers.invoke(new Helpers.Invoker<AnnotationHelper>() {
			public void call(AtomicReference<AnnotationHelper> _helper) {
				methodHelper.addAnnotation(annotationClass, _helper);
			}
		});

		for (Method method : annotationClass.getDeclaredMethods()) {
			annotationHelper.withParameter(method.getName(), method.getReturnType());
		}

		annotationHelper.finish();
	}

	private static <
		_MarkerAnnotation extends Annotation
	>
	List<Annotation> findAnnotatedElements(
		Class<_MarkerAnnotation> marker, Annotation...elements
	){
		return findAnnotatedElements(marker, elements, new Function<Annotation, Class<? extends Annotation>>() {
			public Class<? extends Annotation> apply(Annotation input) {
				return input.annotationType();
			}
		});
	}

	private static <
		_MarkerAnnotation extends Annotation,
		_AnnotatedElement extends AnnotatedElement
	>
	List<_AnnotatedElement> findAnnotatedElements(
		Class<_MarkerAnnotation> marker, _AnnotatedElement...elements
	){
		return findAnnotatedElements(marker, elements, Functions.<_AnnotatedElement>identity());
	}

	private static <
		_MarkerAnnotation extends Annotation,
		_AnnotatedElement extends AnnotatedElement,
		_BaseElement
	>
	List<_BaseElement> findAnnotatedElements(
		Class<_MarkerAnnotation> marker, _BaseElement[] elements, Function<_BaseElement, _AnnotatedElement> function
	){
		List<_BaseElement> annotated = new ArrayList<_BaseElement>();

		for (_BaseElement element : elements) {
			_AnnotatedElement annotatedElement = function.apply(element);
			_MarkerAnnotation annotation = annotatedElement.getAnnotation(marker);

			if (annotation != null) {
				annotated.add(element);
			}
		}

		return annotated;
	}
}