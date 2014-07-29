/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.*;
import unquietcode.tools.flapi.MethodParser.JavaType;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;

import java.lang.annotation.Annotation;
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

	protected static final TypeCreationStrategy BUILDER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = ctx.getGeneratedName("", "Builder", state);
			JDefinedClass _interface = ctx.getOrCreateInterface(state.getName(), name);

			if (_interface.typeParams().length == 0) {
				_interface.generify(Constants.RETURN_TYPE_NAME);
			}

			return _interface;
		}
	};

	protected static final class BUILDER_NARROWED_INTERFACE_STRATEGY {
		public static JClass createType(GeneratorContext ctx, StateClass state) {
			JClass type = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);
			return type.narrow(type.typeParams()[0]);
		}
	};

	protected static final TypeCreationStrategy HELPER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			return ctx.getOrCreateInterface(state.getName(), state.getName()+"Helper");
		}
	};

	protected static final TypeCreationStrategy GENERATOR_CLASS_STRATEGY = new TypeCreationStrategy() {
		public JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = state.getName()+"Generator";
			return ctx.getOrCreateClass(state.getName(), name);
		}
	};

	public static final TypeCreationStrategy WRAPPER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = state.getName()+"Builder";
			JDefinedClass parent = ctx.getOrCreateInterface(state.getName(), name);
			JDefinedClass innerClass;

			try {
				innerClass = parent._interface("Start");
			} catch (JClassAlreadyExistsException ex) {
				return ex.getExistingClass();
			}

			JDefinedClass builder = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);
			innerClass._extends(builder.narrow(Void.class));
			return innerClass;
		}
	};

//	public static final TypeCreationStrategy NULL_STRATEGY = new TypeCreationStrategy() {
//		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
//			return null;
//		}
//	};

	// -------------------------------- //

//	public static JAnnotationUse findAnnotation(JMethod method, JClass type) {
//		for (JAnnotationUse annotationUse : method.annotations()) {
//			if (annotationUse.getAnnotationClass().compareTo(type) == 0) {
//				return annotationUse;
//			}
//		}
//
//		return null;
//	}

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

				return type.isArray ? primitive.array() : primitive;
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

		return type.isArray ? clazz.array() : clazz;
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, Transition transition) {
		return addMethod(_class, returnType, mods, transition, false);
	}

	protected JMethod addHelperMethod(JDefinedClass _class, JType returnType, int mods, Transition transition) {
		return addMethod(_class, returnType, mods, transition, true);
	}

	private JMethod addMethod(JDefinedClass _class, JType returnType, int mods, Transition transition, boolean helper) {
		MethodParser parsed = new MethodParser(transition.getMethodSignature());
		JMethod m = _class.method(mods, returnType, parsed.methodName);

		// regular params
		for (Pair<JavaType, String> entry : parsed.params) {
			JType clazz = getType(entry.first);
			m.param(clazz, entry.second);
		}

		// varargs
		if (parsed.varargType != null) {
			JType clazz = getType(parsed.varargType);

			// for helpers, convert to an array parameter
			if (helper) {
				m.param(clazz.array(), parsed.varargName);
			} else {
				m.varParam(clazz, parsed.varargName);
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
			for (Map.Entry<Object, Map<String, Object>> entry : transition.info().getAnnotations().entrySet()) {
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

//                    if (value.getClass().isArray()) {
//
//                        Class<?> componentType = value.getClass().getComponentType();
//                        JAnnotationArrayMember arrayMember = annotation.paramArray(name);
//
//                        int length = Array.getLength(value);
//
//                        for (int i = 0; i < length; i++) {
//                            Object arrayValue = Array.get(value, i);
//
//                            if (componentType == Class.class) {
//                                arrayMember.param((Class<?>) arrayValue);
//                            } else if (componentType == ClassReference.class) {
//                                String fqcn = ((ClassReference) arrayValue).getFQCN();
//                                arrayMember.param(ref(fqcn));
//                            } else if (componentType == Enum.class) {
//                                arrayMember.param((Enum) arrayValue);
//                            } else if (componentType == String.class) {
//                                arrayMember.param((String) arrayValue);
//                            } else if (componentType == Integer.class || componentType == Integer.TYPE) {
//                                arrayMember.param((Integer) arrayValue);
//                            } else if (componentType == Long.class || componentType == Long.TYPE) {
//                                arrayMember.param((Long) arrayValue);
//                            } else if (componentType == Float.class || componentType == Float.TYPE) {
//                                arrayMember.param((Float) arrayValue);
//                            } else if (componentType == Double.class || componentType == Double.TYPE) {
//                                arrayMember.param((Double) arrayValue);
//                            } else if (componentType == Short.class || componentType == Short.TYPE) {
//                                arrayMember.param((Short) arrayValue);
//                            } else if (componentType == Boolean.class || componentType == Boolean.TYPE) {
//                                arrayMember.param((Boolean) arrayValue);
//                            } else if (componentType == Byte.class || componentType == Byte.TYPE) {
//                                arrayMember.param((Byte) arrayValue);
//                            } else {
//                                throw reportInternalError("invalid annotation value type '" + value.getClass() + "'");
//                            }
//                        }
//
//                    } else {

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
                            throw reportInternalError("invalid annotation value type '" + value.getClass() + "'");
                        }
//                    }
                }
            }
		}

		return m;
	}
}