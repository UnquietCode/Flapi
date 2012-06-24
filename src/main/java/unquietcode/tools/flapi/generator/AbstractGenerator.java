/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.outline.Outline;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;

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

	protected JType refUnboxed(Class clazz) {
		if (Void.class.equals(clazz)) {
			return ctx.model.VOID;
		}

		return ctx.model._ref(clazz);
	}

	protected JClass ref(String FQCN) {
		return ctx.model.ref(FQCN);
	}

	protected String makeMethodKey(BlockOutline block, MethodOutline method) {
		return block.getName()+"_"+method.getMethodKey();
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, MethodOutline method) {
		return addMethod(_class, returnType, mods, new MethodParser(method.getMethodSignature()));
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, MethodParser parsed) {
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

	protected JInvocation makeHelperCall(JMethod _method, String methodName) {
		JFieldRef _helper = JExpr.ref(Constants.HELPER_VALUE_NAME);
		JInvocation helperCall = _helper.invoke(methodName);

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

	/*
		For a single invocation, computes the 'next' method, which is the minus method.
		This means that either the method is removed and then added back either with
		a decremented value or not at all. Only works for dynamic methods.
	 */
	protected Set<MethodOutline> computeMinusMethod(Set<MethodOutline> allMethods, MethodOutline method) {
		if (method.isRequired()) {
			return new HashSet<MethodOutline>(allMethods);
		}

		// compute minus method
		Set<MethodOutline> minusMethod = new HashSet<MethodOutline>(allMethods);
		minusMethod.remove(method);

		// only add back if it's not the last instance
		if (method.maxOccurrences > 1) {
			MethodOutline m = method.copy();
			m.maxOccurrences = m.maxOccurrences - 1;
			minusMethod.add(m);
		}

		return minusMethod;
	}
	
	protected JClass getDynamicReturnType(BlockOutline block, Set<MethodOutline> allMethods, MethodOutline method) {
		JDefinedClass builder = getSubsetInterface(block, allMethods);
		JClass returnType;

		// get base type without block chain
		if (method.isTerminal()) {
			if (method.getReturnType() != null) {
				returnType = ref(method.getReturnType());
			} else {
				returnType = builder.typeParams()[0];
			}
		} else if (method.isRequired()) {
			returnType = builder.narrow(builder.typeParams()[0]);
		} else {
			Set<MethodOutline> minusMethod = computeMinusMethod(allMethods, method);
			returnType = getSubsetInterface(block, minusMethod);
			returnType = returnType.narrow(builder.typeParams()[0]);
		}

		// add in the block chain
		for (int i = method.getBlockChain().size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.getBlockChain().get(i);
			JDefinedClass targetBuilder = getTopLevelInterface(targetBlock);

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

	/*
	 * All methods returned from makeCombinations are clones of the originals.
	 */
	protected Set<Set<MethodOutline>> makeCombinations(Set<MethodOutline> methods) {
		Set<Set<MethodOutline>> combinations = new HashSet<Set<MethodOutline>>();
		Stack<Set<MethodOutline>> stack = new Stack<Set<MethodOutline>>();

		// clone and push
		Set<MethodOutline> cloned = new HashSet<MethodOutline>();
		for (MethodOutline method : methods) {
			cloned.add(method.copy());
		}
		stack.push(cloned);

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

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	public JDefinedClass getTopLevelImplementation(BlockOutline block) {
		return getClass("Impl"+ ctx.getGeneratedName(block.getName() + "Builder", block.getDynamicMethods()));
	}

	public JDefinedClass getTopLevelInterface(BlockOutline block) {
		return getInterface(ctx.getGeneratedName(block.getName() + "Builder", block.getDynamicMethods()));
	}

	public JDefinedClass getSubsetInterface(BlockOutline block, Set<MethodOutline> methods) {
		return getInterface(ctx.getGeneratedName(block.getName() + "Builder", methods));
	}

	public JDefinedClass getSubsetImplementation(BlockOutline block, Set<MethodOutline> methods) {
		JDefinedClass aClass = getClass(ctx.getGeneratedName("Impl" + block.getName() + "Builder", methods));
		aClass._implements(BuilderImplementation.class);
		return aClass;
	}

	public JDefinedClass getBaseImplementation(BlockOutline block) {
		JDefinedClass aClass = getClass("Impl" + block.getName() + "Builder");
		aClass._implements(BuilderImplementation.class);
		return aClass;
	}

	public JDefinedClass getBaseInterface(BlockOutline block) {
		return getInterface(block.getName()+"Builder");
	}

	public JDefinedClass getHelperInterface(BlockOutline block) {
		return getInterface(block.getName()+"Helper");
	}

	public JDefinedClass getGeneratorImplementation(BlockOutline block) {
		return getClass(block.getName()+"Generator");
	}
}
