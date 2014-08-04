package unquietcode.tools.flapi.annotations;

import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.helpers.AnnotationsHelperImpl;
import unquietcode.tools.flapi.helpers.DocumentationHelperImpl;
import unquietcode.tools.flapi.helpers.MethodHelperImpl;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 2014-08-03
 */
public class AnnotationIntrospector {
	private final Map<Class<?>, BlockOutline> blocks = new HashMap<Class<?>, BlockOutline>();

	public DescriptorOutline createDescriptor(Class<?> clazz) {
		DescriptorOutline descriptor = new DescriptorOutline();
		descriptor.setPackageName(clazz.getPackage().getName() + ".builder");

		// discover methods and set them on the blocks
		handleClass(descriptor, clazz);
		return descriptor;
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

	private void handleClass(BlockOutline blockOutline, Class<?> blockClass) {
		Builder builder = blockClass.getAnnotation(Builder.class);
		blockOutline.setHelperClass(blockClass);

		// block name
		if (builder != null && !builder.name().trim().isEmpty()) {
			blockOutline.setName(builder.name());
		} else {
			blockOutline.setName(blockClass.getSimpleName());
		}

		for (Method method : blockClass.getDeclaredMethods()) {
			String methodSignature = getMethodSignature(method);
			MethodOutline methodOutline = blockOutline.addMethod(methodSignature);
			handleMethod(methodOutline, method);
		}
	}

	private void handleMethod(MethodOutline methodOutline, Method method) {
		final After after = method.getAnnotation(After.class);
		final Any any = method.getAnnotation(Any.class);
		final AtLeast atLeast = method.getAnnotation(AtLeast.class);
		final AtMost atMost = method.getAnnotation(AtMost.class);
		final Between between = method.getAnnotation(Between.class);
		final Documented documented = method.getAnnotation(Documented.class);
		final Exactly exactly = method.getAnnotation(Exactly.class);
		final Last last = method.getAnnotation(Last.class);

		try {
			exactlyOneAnnotatedElement(MethodQuantifier.class, method.getAnnotations());
		} catch (IllegalStateException ex) {
			throw new DescriptorBuilderException(
				"Only one annotation from " +
				"{@Any, @AtLeast, @AtMost, @Between, @Exactly, @Last}" +
				" is allowed per method."
			);
		}

		final MethodHelperImpl helper = new MethodHelperImpl(methodOutline);
		final Set<Annotation> annotations = new HashSet<Annotation>(Arrays.asList(method.getAnnotations()));

		// @After
		if (after != null) {
			helper.after(after.value());
			annotations.remove(after);
		}

		// @Any
		if (any != null) {
			if (any.group() == Constants.DEFAULT_NULL_INT) {
				helper.any();
			} else {
				helper.any(any.group());
			}

			annotations.remove(any);
		}

		// @AtLeast
		if (atLeast != null) {
			helper.atLeast(atLeast.value());
			annotations.remove(atLeast);
		}

		// @AtMost
		if (atMost != null) {
			if (atMost.group() == Constants.DEFAULT_NULL_INT) {
				helper.atMost(atMost.value());
			} else {
				helper.atMost(atMost.value(), atMost.group());
			}

			annotations.remove(atMost);
		}

		// @Between
		if (between != null) {
			helper.between(between.minInc(), between.maxInc());
			annotations.remove(between);
		}

		// @Exactly
		if (exactly != null) {
			helper.exactly(exactly.value());
			annotations.remove(exactly);
		}

		// @Last
		if (last != null) {
			Class<?> returnType = method.getReturnType();

			if (returnType == void.class) {
				helper.last();
			} else {
				helper.last(returnType);
			}

			annotations.remove(last);
		}

		// ensure that non-terminal methods aren't returning anything
		else {
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
			DocumentationHelperImpl docHelper = new DocumentationHelperImpl(methodOutline);

			for (String docString : documented.value()) {
				docHelper.addContent(docString);
			}

			docHelper.finish();
			annotations.remove(documented);
		}

		// other annotations
		for (Annotation annotation : annotations) {
			handleMethodAnnotation(methodOutline, annotation);
		}

		// block chaining
		for (int i=0; i < method.getParameterTypes().length; ++i) {
			Class<?> parameterType = method.getParameterTypes()[i];
			BlockChain blockChain = getParameterAnnotation(method, i, BlockChain.class);

			if (blockChain != null) {
				if (parameterType != AtomicReference.class) {
					throw new DescriptorBuilderException("@BlockChain parameters must be of type AtomicReference");
				}

				BlockOutline blockOutline = handleClass(blockChain.value());
				methodOutline.getBlockChain().add(blockOutline);
			}
		}
	}
	
	private static void handleMethodAnnotation(MethodOutline methodOutline, Annotation annotation) {
		Class<? extends Annotation> annotationClass = annotation.annotationType();
		AnnotationsHelperImpl helper = new AnnotationsHelperImpl(methodOutline, annotationClass);

		for (Method method : annotationClass.getDeclaredMethods()) {
			helper.withParameter(method.getName(), method.getReturnType());
		}

		helper.finish();
	}

	private static String getMethodSignature(Method method) {
		StringBuilder signature = new StringBuilder();
		signature.append(method.getName()).append("(");

		Class<?>[] parameterTypes = method.getParameterTypes();

		for (int i=0; i < parameterTypes.length; i++) {
			Class<?> parameterType = parameterTypes[i];

			// skip block chain parameters
			if (getParameterAnnotation(method, i, BlockChain.class) != null) {
				continue;
			}
			
			if (i > 0) {
				signature.append(", ");
			}

			signature.append(parameterType).append(" p").append(i);
		}

		signature.append(")");
		return signature.toString();
	}

	private static <T extends Annotation> T getParameterAnnotation(Method method, int parameterIndex, Class<T> annotationClass) {
		for (Annotation annotation : method.getParameterAnnotations()[parameterIndex]) {
			if (annotation.annotationType() == annotationClass) {
				@SuppressWarnings("unchecked") T annotation1 = (T) annotation;
				return annotation1;
			}
		}

		return null;
	}

	private static <T extends Annotation> Annotation exactlyOneAnnotatedElement(Class<T> marker, Annotation...elements) {
		AnnotatedElement[] annotatedElements = new AnnotatedElement[elements.length];

		for (int i=0; i < elements.length; ++i) {
			annotatedElements[i] = elements[i].annotationType();
		}

		AnnotatedElement annotatedElement = exactlyOneAnnotatedElement(marker, annotatedElements);

		for (Annotation element : elements) {
			if (element.annotationType() == annotatedElement) {
				return element;
			}
		}

		throw new IllegalStateException("expected to find annotation by type");
	}

	private static <T extends Annotation, Z extends AnnotatedElement> Z exactlyOneAnnotatedElement(Class<T> marker, Z...elements) {
		List<Z> annotatedElements = findAnnotatedElements(marker, elements);

		if (annotatedElements.size() != 1) {
			throw new IllegalStateException("expected exactly one non-null value");
		}

		return annotatedElements.get(0);
	}

	private static <T extends Annotation, Z extends AnnotatedElement> List<Z> findAnnotatedElements(Class<T> marker, Z...elements) {
		List<Z> annotated = new ArrayList<Z>();

		for (Z element : elements) {
			if (element != null) {
				T annotation = element.getAnnotation(marker);

				if (annotation != null) {
					annotated.add(element);
				}
			}
		}

		return annotated;
	}
}