/*********************************************************************
 Copyright 2015 the Flapi authors

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

package unquietcode.tools.flapi.java;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Ben Fagin
 */
public class JavaType {
	public final String typeName;
	public final List<JavaType> typeParameters;
	public final int arrayDepth;


	public static JavaType from(String typeName) throws ParseException {
		MethodSignature fake = new MethodSignature(typeName+" fake()");
		return fake.returnType;
	}

	// TODO generics support?

	public static JavaType from(Class<?> clazz) {
		Class<?> c = clazz;
		int depth = 0;

		while (c.isArray()) {
			++depth;
			c = c.getComponentType();
		}

		return new JavaType(c.getName(), depth, Collections.<JavaType>emptyList());
	}

	public static JavaType wildcard() {
		return new JavaType("?", 0, Collections.<JavaType>emptyList());
	}

	public JavaType(String typeName, int arrayDepth, List<JavaType> typeParameters) {
		this.typeName = typeName;
		this.typeParameters = Collections.unmodifiableList(typeParameters);

		checkArgument(arrayDepth >= 0);
		this.arrayDepth = arrayDepth;
	}

	/*package*/ JavaType(JavaType other, int arrayDepth) {
		this.typeName = other.typeName;
		this.typeParameters = other.typeParameters;

		checkArgument(arrayDepth >= 0);
		this.arrayDepth = arrayDepth;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(typeName);

		for (int i=0; i < arrayDepth; ++i) {
			sb.append("[]");
		}

		if (!typeParameters.isEmpty()) {
			sb.append("<");
			boolean first = true;

			for (JavaType typeParameter : typeParameters) {
				if (!first) { sb.append(", "); }
				else { first = false; }
				sb.append(typeParameter);
			}

			sb.append(">");
		}

		return sb.toString();
	}

	public boolean compilerEquivalent(JavaType other) {

		// array check
		if (this.arrayDepth != other.arrayDepth) {
			return false;
		}

		return typeName.equals(other.typeName);
	}

	private static final String[] VOID_TYPES = {
		void.class.getSimpleName(), void.class.getName(),
		Void.class.getSimpleName(), Void.class.getName()
	};

	public boolean isVoid() {
		for (String type : VOID_TYPES) {
			if (type.equals(typeName)) {
				return true;
			}
		}

		return false;
	}
}