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

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author Ben Fagin
 * @version 2014-09-07
 */
public class IntrospectorSupport {

	protected static String getMethodSignature(Method method) {
		return getMethodSignature(method, null);
	}

	protected static String getMethodSignature(final Method method, final String normalizedName) {
		StringBuilder signature = new StringBuilder();

		String name = normalizedName != null ? normalizedName : method.getName();
		signature.append(name).append("(");

		Class<?>[] parameterTypes = method.getParameterTypes();

		for (int i=0; i < parameterTypes.length; i++) {
			Class<?> parameterType = parameterTypes[i];

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
}
