package unquietcode.tools.flapi.annotations;

import com.google.common.base.Function;
import com.google.common.base.Functions;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkState;

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
		Block block = blockClass.getAnnotation(Block.class);
		blockOutline.setHelperClass(blockClass);

		// block name
		if (block != null && !block.name().trim().isEmpty()) {
			blockOutline.setName(block.name());
		} else {
			blockOutline.setName(blockClass.getSimpleName());
		}

		for (Method method : blockClass.getDeclaredMethods()) {
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

		final MethodHelperImpl helper = new MethodHelperImpl(methodOutline);

		// @After
		if (after != null) {
			helper.after(after.value());
		}

		// @Any
		if (any != null) {
			if (any.group() == Constants.DEFAULT_NULL_INT) {
				helper.any();
			} else {
				helper.any(any.group());
			}
		}

		// @AtLeast
		if (atLeast != null) {
			helper.atLeast(atLeast.value());
		}

		// @AtMost
		if (atMost != null) {
			if (atMost.group() == Constants.DEFAULT_NULL_INT) {
				helper.atMost(atMost.value());
			} else {
				helper.atMost(atMost.value(), atMost.group());
			}
		}

		// @Between
		if (between != null) {
			helper.between(between.minInc(), between.maxInc());
		}

		// @Exactly
		if (exactly != null) {
			helper.exactly(exactly.value());
		}

		// @Last
		if (last != null) {
			Class<?> returnType = method.getReturnType();

			if (returnType == void.class) {
				helper.last();
			} else {
				helper.last(returnType);
			}
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
		}

		// other annotations
		for (Annotation annotation : method.getAnnotations()) {

			// skip over Flapi's own annotation types
			if (annotation.annotationType().getAnnotation(FlapiAnnotation.class) != null) {
				continue;
			}
			handleMethodAnnotation(methodOutline, annotation);
		}

		// block chaining
		boolean weAreAtTheEnd = false;

		for (int i=0; i < method.getParameterTypes().length; ++i) {
			Class<?> parameterType = method.getParameterTypes()[i];
			BlockChain blockChain = getParameterAnnotation(method, i, BlockChain.class);

			if (blockChain != null) {
				weAreAtTheEnd = true;

				// check type
				if (parameterType != AtomicReference.class) {
					throw new DescriptorBuilderException("@BlockChain parameters must be of type AtomicReference");
				}

				BlockOutline blockOutline = handleClass(blockChain.value());
				methodOutline.getBlockChain().add(blockOutline);
			}

			else {

				// We can't currently support block helpers anywhere but the final position
				if (weAreAtTheEnd) {
					throw new DescriptorBuilderException(
						"@BlockChain parameters are currently only supported as the last arguments to the method"
					);
				}
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

			final String typeName;

			// varargs
			if (i == parameterTypes.length-1 && method.isVarArgs()) {
				checkState(parameterType.isArray());
				typeName = parameterType.getComponentType().getName()+"...";
			}

			// arrays
			else if (parameterType.isArray()) {
				typeName = parameterType.getComponentType().getName()+"[]";
			}

			// normal
			else {
				typeName = parameterType.getName();
			}

			signature.append(typeName).append(" p").append(i);
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