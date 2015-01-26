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
import unquietcode.tools.flapi.ClassReference;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.Pair;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.java.JavaType;
import unquietcode.tools.flapi.java.MethodSignature;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 08-06-2012
 */
public abstract class AbstractGenerator {
	protected final GeneratorContext ctx;

	protected AbstractGenerator(GeneratorContext ctx) {
		this.ctx = ctx;
	}

	// ------- type creation strategies ------- //

	protected static final TypeCreationStrategy BUILDER_INTERFACE_STRATEGY = new DefaultTypeCreationStrategy() {
		public @Override JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			final String name = ctx.getGeneratedName(state);
			final Pair<JDefinedClass, Boolean> construction = ctx.getOrCreateInterface(state.getName(), name);
			final JDefinedClass _interface = construction.first;
			final boolean created = construction.second;

			// if it has yet to have the type parameter added
			if (_interface.typeParams().length == 0) {
				_interface.generify(Constants.RETURN_TYPE_NAME);
			}

			// if newly constructed
			if (created) {

				// create the wrapper interface
				if (state.isTopLevel()) {
					WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, state);
				}

				// add @see annotation pointing back to helper
				JClass helperClass = HELPER_INTERFACE_STRATEGY.createWeakType(ctx, state);
				_interface.javadoc().append("\n@see "+helperClass.fullName());
			}

			return _interface;
		}
	};

	protected static final TypeCreationStrategy BUILDER_NARROWED_INTERFACE_STRATEGY = new TypeCreationStrategy() {

		@Override
		public JClass createWeakType(GeneratorContext ctx, StateClass state) {
			JDefinedClass type = BUILDER_INTERFACE_STRATEGY.createStrongType(ctx, state);
			return type.narrow(type.typeParams()[0]);
		}

		@Override
		public JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			throw new UnsupportedOperationException("strong type not supported");
		}
	};

	protected static final TypeCreationStrategy BUILDER_OR_WRAPPER_INTERFACE_STRATEGY = new DefaultTypeCreationStrategy() {

		@Override
		public JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			if (state.isTopLevel()) {
				return WRAPPER_INTERFACE_STRATEGY.createStrongType(ctx, state);
			} else {
				return BUILDER_INTERFACE_STRATEGY.createStrongType(ctx, state);
			}
		}
	};

	protected static final TypeCreationStrategy BUILDER_OR_WRAPPER_NARROWED_INTERFACE_STRATEGY = new TypeCreationStrategy() {

		@Override
		public JClass createWeakType(GeneratorContext ctx, StateClass state) {
			if (state.isTopLevel()) {
				JClass builder = BUILDER_NARROWED_INTERFACE_STRATEGY.createWeakType(ctx, state);
				JClass wrapper = WRAPPER_INTERFACE_STRATEGY.createWeakType(ctx, state);
				return wrapper.narrow(builder.getTypeParameters().get(0));
			} else {
				return BUILDER_NARROWED_INTERFACE_STRATEGY.createWeakType(ctx, state);
			}
		}

		@Override
		public JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			throw new UnsupportedOperationException("nope");
		}
	};

	protected static final TypeCreationStrategy HELPER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JClass createWeakType(GeneratorContext ctx, StateClass state) {
			if (state.getHelperClass() != null) {
				return ctx.model.ref(state.getHelperClass());
			} else {
				return createStrongType(ctx, state);
			}
		}

		public @Override JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			if (state.getHelperClass() != null) {
				return null;
			} else {
				String name = ctx.getNameGenerator().helperName(state.getName());
				return ctx.getOrCreateInterface(state.getName(), name).first;
			}
		}
	};

	protected static final TypeCreationStrategy GENERATOR_CLASS_STRATEGY = new DefaultTypeCreationStrategy() {
		public JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			String name = ctx.getNameGenerator().generatorName(state.getName());
			return ctx.getOrCreateClass(state.getName(), name).first;
		}
	};

	protected static final TypeCreationStrategy FACTORY_INTERFACE_STRATEGY = new DefaultTypeCreationStrategy() {
		public JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			String name = ctx.getNameGenerator().factoryName(state.getName());
			return ctx.getOrCreateInterface(state.getName(), name).first;
		}
	};

	public static final TypeCreationStrategy WRAPPER_INTERFACE_STRATEGY = new DefaultTypeCreationStrategy() {
		public @Override JDefinedClass createStrongType(GeneratorContext ctx, StateClass state) {
			final String builderName = ctx.getNameGenerator().builderName(state.getName());
			final JDefinedClass parent = ctx.getOrCreateInterface(state.getName(), builderName).first;
			JDefinedClass startClass;

			try {
				String wrapperName = ctx.getNameGenerator().wrapperName(state.getName());
				startClass = parent._interface(wrapperName);
			} catch (JClassAlreadyExistsException ex) {
				return ex.getExistingClass();
			}

			JTypeVar startReturnType = startClass.generify(Constants.RETURN_TYPE_NAME);
			JClass builder = BUILDER_INTERFACE_STRATEGY.createWeakType(ctx, state);
			startClass = startClass._extends(builder.narrow(startReturnType));

			return startClass;
		}
	};

	protected static DescriptorBuilderException reportInternalError(String msg) throws DescriptorBuilderException {
		msg += " (this is an internal error)";
		throw new DescriptorBuilderException(msg);
	}

	protected JClass ref(Class clazz) {
		return ctx.model.ref(clazz);
	}

	protected JClass ref(String fqcn) {
		return ctx.model.ref(fqcn);
	}

	protected JType getType(JavaType type) {
		JClass clazz;
		String name = type.typeName;

		dance: {
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass(name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// try java.lang package
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass("java.lang."+name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// try java.util package
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass("java.util."+name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// maybe it's a primitive
			try {
				JType primitive = ctx.model.parseType(name);

				if (!type.typeParameters.isEmpty()) {
					throw new DescriptorBuilderException("Primitive '"+primitive.name()+"' cannot have type parameters.");
				}

				return arrayMaybe(type.arrayDepth, primitive);
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// Maybe it's just not visible. Let the code model decide.
			clazz = ctx.model.ref(name);
		}

		for (JavaType tp : type.typeParameters) {
			JType t = getType(tp);

			if (t.isPrimitive()) {
				throw new DescriptorBuilderException("Primitive '"+t.name()+"' cannot be used as a type parameter.");
			}

			clazz = clazz.narrow(t);
		}

		return arrayMaybe(type.arrayDepth, clazz);
	}

	private static JType arrayMaybe(int depth, JType type) {
		for (int i=0; i < depth; ++i) {
			type = type.array();
		}

		return type;
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, Transition transition) {
		return addMethod(_class, returnType, mods, transition, false);
	}

	protected JMethod addHelperMethod(JDefinedClass _class, JType returnType, int mods, Transition transition) {
		return addMethod(_class, returnType, mods, transition, true);
	}

	private JMethod addMethod(JDefinedClass _class, JType returnType, int mods, Transition transition, boolean helper) {
		MethodSignature methodSignature = transition.getMethodSignature();
		JMethod m = _class.method(mods, returnType, methodSignature.methodName);

		// regular params
		for (Pair<JavaType, String> entry : methodSignature.params) {
			JType clazz = getType(entry.first);
			m.param(clazz, entry.second);
		}

		// varargs
		if (methodSignature.varargType != null) {
			JType clazz = getType(methodSignature.varargType);

			// for helpers, convert to an array parameter
			if (helper) {
				m.param(clazz.array(), methodSignature.varargName);
			} else {
				m.varParam(clazz, methodSignature.varargName);
			}
		}

		// documentation
		if (transition.info().getDocumentation() != null) {
			m.javadoc().append(transition.info().getDocumentation());
		}

		// deprecation
		if (transition.info().isDeprecated()) {
			m.annotate(Deprecated.class);
			JCommentPart doc = m.javadoc().addDeprecated();

			String docString = transition.info().getDeprecationReason();
			if (docString != null && !(docString = docString.trim()).isEmpty()) {
				doc.append(docString);
			}
		}

		// other annotations
		if (!transition.info().getAnnotations().isEmpty()) {
			addMethodAnnotations(m, transition.info().getAnnotations());
		}

		return m;
	}

	private void addMethodAnnotations(JMethod m, Map<Object, Map<String, Object>> annotations) {
		for (Map.Entry<Object, Map<String, Object>> entry : annotations.entrySet()) {
			Object annotationType = entry.getKey();
			final JAnnotationUse annotation;

			if (annotationType instanceof Class) {
				@SuppressWarnings("unchecked") JAnnotationUse _annotation = m.annotate((Class) annotationType);
				annotation = _annotation;
			} else if (annotationType instanceof ClassReference) {
				String fqcn = ((ClassReference) annotationType).getFQCN();
				annotation = m.annotate(ref(fqcn));
			} else {
				throw reportInternalError("invalid annotation type '"+annotationType.getClass()+"'");
			}

			final Set<String> seenParameterNames = new HashSet<String>();

			for (Map.Entry<String, Object> parameter : entry.getValue().entrySet()) {
				final String name = parameter.getKey();
				final Object value = parameter.getValue();

				if (seenParameterNames.contains(name)) {
					String msg = String.format("duplicate parameter '%s' for annotation on method '%s'", name, m.name());
					throw new DescriptorBuilderException(msg);
				}
				seenParameterNames.add(name);

				// array parameters
				if (value.getClass().isArray()) {

					Class<?> componentType = value.getClass().getComponentType();
					JAnnotationArrayMember arrayMember = annotation.paramArray(name);

					int length = Array.getLength(value);

					for (int i = 0; i < length; i++) {
						Object arrayValue = Array.get(value, i);
                        addAnnotationArrayParameter(arrayMember, componentType, arrayValue);
					}

                // regular parameters
				} else {
					addAnnotationParameter(annotation, name, value);
				}
			}
		}
	}

	private void addAnnotationParameter(JAnnotationUse annotation, String name, Object value) {
		if (value instanceof Class) {
			annotation.param(name, (Class<?>) value);
		} else if (value instanceof ClassReference) {
			String fqcn = ((ClassReference) value).getFQCN();
			annotation.param(name, ref(fqcn));
		} else if (value instanceof Enum) {
			annotation.param(name, (Enum) value);
		} else if (value instanceof String) {
			annotation.param(name, (String) value);
		} else if (value instanceof Integer) {
			annotation.param(name, (Integer) value);
		} else if (value instanceof Long) {
			annotation.param(name, (Long) value);
		} else if (value instanceof Float) {
			annotation.param(name, (Float) value);
		} else if (value instanceof Double) {
			annotation.param(name, (Double) value);
		} else if (value instanceof Short) {
			annotation.param(name, (Short) value);
		} else if (value instanceof Boolean) {
			annotation.param(name, (Boolean) value);
		} else if (value instanceof Byte) {
			annotation.param(name, (Byte) value);
		} else {
			throw reportInternalError("invalid annotation value type '"+value.getClass()+"'");
		}
	}

	private void addAnnotationArrayParameter(JAnnotationArrayMember arrayMember, Class<?> componentType, Object arrayValue) {
		if (componentType == Class.class) {
			arrayMember.param((Class<?>) arrayValue);
		} else if (componentType == ClassReference.class) {
			String fqcn = ((ClassReference) arrayValue).getFQCN();
			arrayMember.param(ref(fqcn));
		} else if (componentType == Enum.class) {
			arrayMember.param((Enum) arrayValue);
		} else if (componentType == String.class) {
			arrayMember.param((String) arrayValue);
		} else if (componentType == Integer.TYPE) {
			arrayMember.param((Integer) arrayValue);
		} else if (componentType == Long.TYPE) {
			arrayMember.param((Long) arrayValue);
		} else if (componentType == Float.TYPE) {
			arrayMember.param((Float) arrayValue);
		} else if (componentType == Double.TYPE) {
			arrayMember.param((Double) arrayValue);
		} else if (componentType == Short.TYPE) {
			arrayMember.param((Short) arrayValue);
		} else if (componentType == Boolean.TYPE) {
			arrayMember.param((Boolean) arrayValue);
		} else if (componentType == Byte.TYPE) {
			arrayMember.param((Byte) arrayValue);
		} else {
			throw reportInternalError("invalid annotation value type '"+arrayValue.getClass()+"'");
		}
	}
}