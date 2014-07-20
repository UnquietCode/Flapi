/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.MethodParser.JavaType;
import unquietcode.tools.flapi.Pair;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;

import java.lang.annotation.Annotation;
import java.util.Map;

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

	public static final TypeCreationStrategy NULL_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			return null;
		}
	};

	// -------------------------------- //

	public static JAnnotationUse findAnnotation(JMethod method, JClass type) {
		for (JAnnotationUse annotationUse : method.annotations()) {
			if (annotationUse.getAnnotationClass().compareTo(type) == 0) {
				return annotationUse;
			}
		}

		return null;
	}

	protected JClass ref(Class clazz) {
		return ctx.model.ref(clazz);
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

        if (!transition.info().getAnnotations().isEmpty()) {
            for (Map.Entry<String, Map<String, Object>> entry : transition.info().getAnnotations().entrySet()) {

                JAnnotationUse annotate = m.annotate(ctx.model.directClass(entry.getKey()));
                Map<String, Class> types = transition.info().getAnnotationTypes().get(entry.getKey());

                for (Map.Entry<String, Object> paramEntry : entry.getValue().entrySet()) {
                    Object value = paramEntry.getValue();
                    Class type = types.get(paramEntry.getKey());

                    if (type == Class.class && value instanceof Class)
                        annotate.param(paramEntry.getKey(), (Class) value);
                    else if (type == Class.class && value instanceof String)
                        annotate.param(paramEntry.getKey(), ctx.model.directClass((String) value));
                    else if (value instanceof Enum)
                        annotate.param(paramEntry.getKey(), (Enum) value);
                    else if (value instanceof String)
                        annotate.param(paramEntry.getKey(), (String) value);
                    else if (value instanceof Integer)
                        annotate.param(paramEntry.getKey(), (Integer) value);
                    else if (value instanceof Long)
                        annotate.param(paramEntry.getKey(), (Long) value);
                    else if (value instanceof Float)
                        annotate.param(paramEntry.getKey(), (Float) value);
                    else if (value instanceof Double)
                        annotate.param(paramEntry.getKey(), (Double) value);
                    else if (value instanceof Short)
                        annotate.param(paramEntry.getKey(), (Short) value);
                    else if (value instanceof Boolean)
                        annotate.param(paramEntry.getKey(), (Boolean) value);
                    else if (value instanceof Byte)
                        annotate.param(paramEntry.getKey(), (Byte) value);
                }
            }
        }

        return m;
    }
}
