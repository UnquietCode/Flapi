package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.Pair;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.outline.Outline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-11-2012
 */
public abstract class AbstractGenerator<_InType extends Outline, _OutType> implements Generator<_InType, _OutType> {
	protected final GeneratorContext ctx;
	protected final _InType outline;
	
	protected AbstractGenerator(_InType outline, GeneratorContext context) {
		this.outline = outline;
		ctx = context;
	}

	public JDefinedClass getInterface(String name) {
		return ctx.getOrCreateInterface(name);
	}

	public JDefinedClass getClass(String name) {
		return ctx.getOrCreateClass(name);
	}
	
	protected JClass ref(Class clazz) {
		return ctx.model.ref(clazz);
	}

	protected JClass ref(String FQCN) {
		return ctx.model.ref(FQCN);
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, MethodOutline method) {
		MethodParser parsed = new MethodParser(method.methodSignature);
		JMethod m = _class.method(mods, returnType, parsed.methodName);

		// regular params
		for (Map.Entry<String, String> entry : parsed.params.entrySet()) {
			JType clazz = getType(entry.getValue());
			m.param(clazz, entry.getKey());
		}

		// varargs
		if (parsed.varargType != null) {
			JType clazz = getType(parsed.varargType);
			m.varParam(clazz, parsed.varargName);
		}

		return m;
	}

	protected JInvocation makeHelperCall(JMethod _method, MethodOutline method) {
		JFieldRef _helper = JExpr.ref("_helper");
		JInvocation helperCall = _helper.invoke(_method.name());

		// normal args
		for (JVar param : _method.listParams()) {
			helperCall.arg(param);
		}

		// vararg
		if (_method.listVarParam() != null) {
			helperCall.arg(_method.listVarParam());
		}

		return helperCall;
	}

//	public JClass getMinusMethodInterface

	private JClass getMinusMethodType(String name, MethodOutline method, Set<MethodOutline> allMethods, boolean interfaceDesired) {
		Set<MethodOutline> minusMethod = new HashSet<MethodOutline>(allMethods);
		minusMethod.remove(method);

		// only add back if it's not the last instance
		if (method.maxOccurrances > 1) {
			MethodOutline m = method.copy();
			m.maxOccurrances = m.maxOccurrances - 1;
			minusMethod.add(m);
		}

		JClass iType = getInterface(getGeneratedName(name, minusMethod));
		if (interfaceDesired) {
			// if it's the base class, we need the self-type
			if (minusMethod.isEmpty()) {
				return iType.narrow(iType);
			} else {
				return iType;
			}
		}

		JClass cType = getClass(getGeneratedName("Impl"+name, minusMethod));
		if (minusMethod.isEmpty()) {
			return cType.narrow(iType);
		} else {
			return cType;
		}
	}
	
	protected JClass getDynamicReturnType(BlockOutline block, Set<MethodOutline> allMethods, MethodOutline method, boolean interfaceDesired) {
		JDefinedClass builder = interfaceDesired
							  ? getInterface(getGeneratedName(block.getBaseInterface(), allMethods))
							  : getClass(getGeneratedName(block.getBaseImplementation(), allMethods));
		JClass returnType;

		// get base type without block chain
		if (method.isTerminal()) {
			returnType = builder.typeParams()[0];
		} else if (method.isRequired()) {
			returnType = builder.narrow(builder.typeParams()[0]);
		} else {
			// compute minus method
			Set<MethodOutline> minusMethod = new HashSet<MethodOutline>(allMethods);
			minusMethod.remove(method);

			// only add back if it's not the last instance
			if (method.maxOccurrances > 1) {
				MethodOutline m = method.copy();
				m.maxOccurrances = m.maxOccurrances - 1;
				minusMethod.add(m);
			}

			if (interfaceDesired) {
				returnType = getInterface(getGeneratedName(block.getBaseInterface(), minusMethod));
				returnType = returnType.narrow(builder.typeParams()[0]);
			} else {
				returnType = getClass(getGeneratedName(block.getBaseImplementation(), minusMethod));
			}
		}

		// add in the block chain
		for (int i = method.blockChain.size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.blockChain.get(i);
			JDefinedClass targetBuilder = getInterface(targetBlock.getTopLevelInterface());

			returnType = targetBuilder.narrow(returnType);
		}

		return returnType;
	}


	private JClass getReturnType(String name, MethodOutline method, Set<MethodOutline> allMethods, boolean interfaceDesired) {
		Set<MethodOutline> minusMethod = new HashSet<MethodOutline>(allMethods);
		minusMethod.remove(method);

		// only add back if it's not the last instance
		if (method.maxOccurrances > 1) {
			MethodOutline m = method.copy();
			m.maxOccurrances = m.maxOccurrances - 1;
			minusMethod.add(m);
		}

		JClass iType = getInterface(getGeneratedName(name, minusMethod));
		if (interfaceDesired) {
			// if it's the base class, we need the self-type
			if (minusMethod.isEmpty()) {
				return iType.narrow(iType);
			} else {
				return iType;
			}
		}

		JClass cType = getClass(getGeneratedName("Impl"+name, minusMethod));
		if (minusMethod.isEmpty()) {
			return cType.narrow(iType);
		} else {
			return cType;
		}
	}


	private JType getType(String name) {
		JType clazz;
		JCodeModel model = ctx.model;

		if ("boolean".equals(name)) {
			clazz = model.BOOLEAN;
		} else if ("int".equals(name)) {
			clazz = model.INT;
		} else if ("byte".equals(name)) {
			clazz = model.BYTE;
		} else if ("short".equals(name)) {
			clazz = model.SHORT;
		} else if ("float".equals(name)) {
			clazz = model.FLOAT;
		} else if ("double".equals(name)) {
			clazz = model.DOUBLE;
		} else {
			// expect fully qualified class name
			clazz = model._getClass(name);

			// try java.lang package
			if (clazz == null) {
				clazz = model.ref("java.lang."+name);
			}

			// fail
			if (clazz == null) {
				throw new RuntimeException("Could not find type reference for type name '"+ name +"'.");
			}
		}

		return clazz;
	}

	protected Set<Set<MethodOutline>> makeCombinations(Set<MethodOutline> methods) {
		Set<Set<MethodOutline>> combinations = new HashSet<Set<MethodOutline>>();
		Stack<Set<MethodOutline>> stack = new Stack<Set<MethodOutline>>();
		stack.push(methods);

		while (!stack.isEmpty()) {
			Set<MethodOutline> set = stack.pop();
			combinations.add(set);

			for (MethodOutline method : set) {
				Set<MethodOutline> next = new HashSet<MethodOutline>(set);
				boolean changed = false;

				// only remove if not required
				if (!method.isRequired()) {
					next.remove(method);
					changed = true;
				}

				// if we can afford to lose one occurrence then do it
				if (method.maxOccurrances > 1) {
					MethodOutline m = method.copy();

					m.maxOccurrances = m.maxOccurrances - 1;
					next.add(m);
					changed = true;
				}

				// only push if we've made useful changes
				if (changed) {
					// don't include empty sets  (this practice is debatable)
					if (!next.isEmpty()) {
						stack.push(next);
					}
				}
			}
		}

		return combinations;
	}

	public static String getGeneratedName(String suffix, Set<MethodOutline> methods) {
		StringBuilder name = new StringBuilder();
		name.append(name).append(suffix);


		// TODO change to mapped names after debugging (if condensed parameter)

		for (MethodOutline method : methods) {
			MethodParser parsed = new MethodParser(method.methodSignature);
			name.append("_").append(parsed.methodName);
			//.append(nameMap.get(getMethodName(method.methodSignature)))

			if (method.maxOccurrances > 1) {
				name.append("x").append(method.maxOccurrances);
			}
		}

		return name.toString();
	}
	

}
