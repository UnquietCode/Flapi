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

package unquietcode.tools.flapi;

import unquietcode.tools.flapi.annotations.BlockChain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author Ben Fagin
 * @version 2014-09-07
 */
public class IntrospectorSupport {

	protected static <T extends Annotation> T getParameterAnnotation(Method method, int parameterIndex, Class<T> annotationClass) {
		for (Annotation annotation : method.getParameterAnnotations()[parameterIndex]) {
			if (annotation.annotationType() == annotationClass) {
				@SuppressWarnings("unchecked") T annotation1 = (T) annotation;
				return annotation1;
			}
		}

		return null;
	}

	protected static <T extends Annotation> boolean hasParameterAnnotation(Method method, int parameterIndex, Class<T> annotationClass) {
		return getParameterAnnotation(method, parameterIndex, annotationClass) != null;
	}

	protected static String getMethodSignature(Method method) {
		return getMethodSignature(method, null);
	}

	protected static String getMethodSignature(final Method method, final String normalizedName) {
		StringBuilder signature = new StringBuilder();

		String name = normalizedName != null ? normalizedName : method.getName();
		signature.append(name).append("(");

		boolean first = true;
		Class<?>[] parameterTypes = method.getParameterTypes();

		for (int i=0; i < parameterTypes.length; i++) {
			Class<?> parameterType = parameterTypes[i];

			// skip BlockChain parameters
			if (hasParameterAnnotation(method, i, BlockChain.class)) {
				continue;
			}

			if (!first) {
				signature.append(", ");
			} else {
				first = false;
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
}