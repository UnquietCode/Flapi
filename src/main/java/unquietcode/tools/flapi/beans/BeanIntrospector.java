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

package unquietcode.tools.flapi.beans;

import unquietcode.tools.flapi.IntrospectorSupport;
import unquietcode.tools.flapi.builder.Annotation.AnnotationHelper;
import unquietcode.tools.flapi.helpers.MethodHelperImpl;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.runtime.OriginalMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Introspector which looks for bean setXYZ (and withXYZ) methods and
 * constructs a builder which can set these properties (at most once
 * per property) and then return a completed bean.
 *
 * @author Ben Fagin
 * @version 2014-09-06
 */
public class BeanIntrospector extends IntrospectorSupport {

	public DescriptorOutline createDescriptor(Class<?> clazz) {
		return createDescriptor(clazz, "build");
	}

	public DescriptorOutline createDescriptor(Class<?> clazz, String doneMethodName) {
		DescriptorOutline descriptor = new DescriptorOutline();
		descriptor.setPackageName(clazz.getPackage().getName()+".builder");
		descriptor.setBeanClass(clazz);
		descriptor.setName(clazz.getSimpleName());

		for (Method method : clazz.getDeclaredMethods()) {
			final boolean isSoloWithMethod = isSoloWithMethod(method);

			if (!isSetter(method) && !isSoloWithMethod) {
				continue;
			}

			String methodSignature = getNormalizedMethodSignature(method);
			MethodOutline methodOutline = descriptor.addMethod(methodSignature);
			MethodHelperImpl helper = new MethodHelperImpl(methodOutline);
			helper.atMost(1);

			// mark transformed methods with the original method name
			if (isSoloWithMethod) {
				AtomicReference<AnnotationHelper> annotationHelper = new AtomicReference<AnnotationHelper>();
				helper.addAnnotation(OriginalMethod.class, annotationHelper);
				annotationHelper.get().withParameter("value", method.getName());
				annotationHelper.get().finish();
			}
		}

		// add a terminal method
		MethodOutline doneMethod = descriptor.addMethod(doneMethodName+"()");
		MethodHelperImpl methodHelper = new MethodHelperImpl(doneMethod);
		methodHelper.last(clazz);

		// mark as a transformed method
		AtomicReference<AnnotationHelper> annotationHelper = new AtomicReference<AnnotationHelper>();
		methodHelper.addAnnotation(OriginalMethod.class, annotationHelper);
		annotationHelper.get().finish();

		return descriptor;
	}

	private static boolean isSetter(Method method) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1) {
		    if (method.getReturnType().equals(void.class) && method.getName().matches("^set[A-Z].*")) {
				return true;
			}
		}

		return false;
	}

	private static boolean isSoloWithMethod(Method method) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1) {
			if (method.getDeclaringClass().isAssignableFrom(method.getReturnType()) && method.getName().matches("^with[A-Z].*")) {

				// check that a setter doesn't already exist
				final String setterName = "set"+method.getName().substring(4);

				try {
					method.getDeclaringClass().getMethod(setterName, method.getParameterTypes());
				} catch (NoSuchMethodException ex) {
					return true;
				}
			}
		}

		return false;
	}

	private static String getNormalizedMethodSignature(Method method) {
		String name = method.getName();

		// normalize to setXYZ
		if (name.startsWith("with")) {
			name = "set"+name.substring(4);
		}

		return getMethodSignature(method, name);
	}
}