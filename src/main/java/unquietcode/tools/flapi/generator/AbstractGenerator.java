package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;
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
		for (Pair<String, String> entry : parsed.params) {
			JType clazz = getType(entry.first);
			m.param(clazz, entry.second);
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
			if (method.maxOccurrences > 1) {
				MethodOutline m = method.copy();
				m.maxOccurrences = m.maxOccurrences - 1;
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
		for (int i = method.getBlockChain().size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.getBlockChain().get(i);
			JDefinedClass targetBuilder = getInterface(targetBlock.getTopLevelInterface());

			returnType = targetBuilder.narrow(returnType);
		}

		return returnType;
	}

	private JType getType(String name) {
		JType clazz;

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

			// Maybe it's primitive, or just not visible. Let the code model decide.
			clazz = ref(name);
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

					// if we can afford to lose one occurrence then do it and re-add
					if (method.maxOccurrences > 1) {
						MethodOutline m = method.copy();

						m.maxOccurrences = m.maxOccurrences - 1;
						next.add(m);
					}
				}

				// only push if we've made useful changes
				if (changed) {

					// don't include empty sets here
					if (!next.isEmpty()) {
						stack.push(next);
					}
				}
			}
		}

		// always add the empty set
		combinations.add(Collections.<MethodOutline>emptySet());
		combinations = deduplicate(combinations);
		return combinations;
	}

	private Set<Set<MethodOutline>> deduplicate(Set<Set<MethodOutline>> combinations) {
		Set<Set<MethodOutline>> retval = new HashSet<Set<MethodOutline>>();
		Set<String> seen = new HashSet<String>();

		// compute the string key for the combination
		// if already seen, don't include in the result set
		for (Set<MethodOutline> combination : combinations) {
			Set<MethodOutline> sorted = new TreeSet<MethodOutline>(combination);
			StringBuilder keyBuilder = new StringBuilder();

			for (MethodOutline method : sorted) {
				keyBuilder.append(method).append("|");
			}

			String key = keyBuilder.toString();

			if (!seen.contains(key)) {
				retval.add(sorted);
				seen.add(key);
			}
		}

		return retval;
	}

	public static String getGeneratedName(String suffix, Set<MethodOutline> methods) {
		StringBuilder name = new StringBuilder();
		name.append(name).append(suffix);

		for (MethodOutline method : new TreeSet<MethodOutline>(methods)) {
			MethodParser parsed = new MethodParser(method.methodSignature);
			name.append("_").append(parsed.methodName);
			//.append(nameMap.get(getMethodName(method.methodSignature)))

			if (method.maxOccurrences > 1) {
				name.append("$").append(method.maxOccurrences);
			}
		}

		return name.toString();
	}
	

}
