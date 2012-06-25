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
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ExpectedInvocationsException;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-11-2012
 * @version 05-27-2012  merged all block generation code together
 * @version 06-24-2012  modified to better support terminal blocks and implicit terminals
 */
public class BlockGenerator extends AbstractGenerator<BlockOutline, Void> {

	public BlockGenerator(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public Void generate() {
		JDefinedClass iHelper = generateHelper();
		generateSubsets();

		for (BlockOutline child : outline.getBlocks()) {
			BlockGenerator childGenerator = new BlockGenerator(child, ctx);
			childGenerator.generate();
		}

		return null;
	}


	//==o==o==o==o==o==o==| helper |==o==o==o==o==o==o==//

	public JDefinedClass generateHelper() {
		JDefinedClass iHelper = getHelperInterface(outline);

		for (MethodOutline method : outline.getAllMethods()) {
			JType returnType = method.getReturnType() != null
							 ? ref(method.getReturnType())
							 : ctx.model.VOID
			;
			JMethod _method = addMethod(iHelper, returnType, JMod.NONE, method);

			// for every block in the chain, add a wrapped helper parameter
			int i=1;
			for (BlockOutline block : method.getBlockChain()) {
				JDefinedClass blockHelper = getHelperInterface(block);
				_method.param(ref(ObjectWrapper.class).narrow(blockHelper), Constants.HELPER_VALUE_NAME+(i++));
			}
		}

		return iHelper;
	}


	//==o==o==o==o==o==o==| subsets |==o==o==o==o==o==o==//

	public void generateSubsets() {
		for (Set<MethodOutline> combination : makeCombinations(outline.getDynamicMethods())) {

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getSubsetInterface(outline, combination);
			iSubset.generify(Constants.RETURN_TYPE_NAME);

			// make the class
			JDefinedClass cSubset = createSubsetImpl(combination);
			addInvocationTracking(cSubset, outline.getAllMethods());

			// add the required methods
			for (MethodOutline method : outline.getRequiredMethods()) {

				// add to interface
				addMethod(iSubset, getDynamicReturnType(outline, combination, method), JMod.NONE, method);

				// add to implementation
				JExpression initialReturnValue = computeInitialReturnValue(combination, method);
				addMethod(cSubset, computeImplementationReturnType(combination, method), initialReturnValue, method);
			}

			// add the dynamic methods
			for (MethodOutline method : combination) {

				// if we should exit when empty, and this is the last one
				if (isCandidateForDynamicTerminal(method, combination)) {
					method.setImplicitTerminal();
				}

				JType returnType = getDynamicReturnType(outline, combination, method);

				// add to interface
				addMethod(iSubset, returnType, JMod.NONE, method);

				// add to implementation
				addMethod(
					cSubset,
					computeImplementationReturnType(combination, method),
					computeInitialReturnValue(combination, method),
					method
				);
			}
		}
	}

	private boolean isCandidateForDynamicTerminal(MethodOutline method, Set<MethodOutline> combination) {
		return outline.getRequiredMethods().isEmpty()      // no required methods
			&& combination.size() == 1                     // this is the only method in the subset
			&& method.maxOccurrences == 1                  // this is the last occurrence of the method
			&& !method.isTerminal()                        // the method should not already be terminal
		;
	}

	private JDefinedClass createSubsetImpl(Set<MethodOutline> methodCombination) {
		JDefinedClass cSubset = getSubsetImplementation(outline, methodCombination);
		JDefinedClass iSubset = getSubsetInterface(outline, methodCombination);
		cSubset._implements(iSubset);

		JType returnType = outline.isTerminal() && outline.getReturnType() != null
						 ? ref(outline.getReturnType())
						 : ref(BuilderImplementation.class)
		;
		JFieldVar _helper = cSubset.field(JMod.PRIVATE+JMod.FINAL, getHelperInterface(outline), Constants.HELPER_VALUE_NAME);
		JFieldVar _returnValue = cSubset.field(JMod.PRIVATE+JMod.FINAL, returnType, Constants.RETURN_VALUE_NAME);

		// constructor
		JMethod constructor = cSubset.constructor(JMod.NONE);
		JVar pHelper = constructor.param(getHelperInterface(outline), "helper");
		JVar pParent = constructor.param(returnType, "returnValue");

		constructor.body().assign(_helper, pHelper);
		constructor.body().assign(_returnValue, pParent);

		// BuilderImplementation _getParent
		JMethod _getParent = cSubset.method(JMod.PUBLIC, ref(BuilderImplementation.class), "_getParent");
		if (!outline.isTerminal()) {
			_getParent.body()._return(_returnValue);
		} else {
			_getParent.body()._return(JExpr._null());
		}

		return cSubset;
	}

	private void addInvocationTracking(JDefinedClass cBuilder, Set<MethodOutline> methods) {
		// filter the methods
		Set<MethodOutline> filtered = new TreeSet<MethodOutline>(methods);
		for (MethodOutline method : methods) {
			if (method.minOccurrences <= 0) {
				filtered.remove(method);
			}
		}
		methods = filtered;

		// fields
		for (MethodOutline method : methods) {
			cBuilder.field(JMod.NONE, ctx.model.INT, "ic_"+makeMethodKey(outline, method), JExpr.lit(method.minOccurrences));
		}

		// copy method
		JMethod _method = cBuilder.method(JMod.PRIVATE, ctx.model.VOID, "_transferInvocations");
		JVar _next = _method.param(ref(Object.class), "next");

		if (methods.isEmpty()) {
			_method.body().directStatement("// nothing");
		} else {
			JVar _clazz = _method.body().decl(ref(Class.class), "clazz", _next.invoke("getClass"));

			for (MethodOutline method : methods) {
				_method.body().directStatement(" ");
				String key = "ic_"+makeMethodKey(outline, method);
				JTryBlock block = _method.body()._try();
				JVar _field = block.body().decl(ref(Field.class), "field", _clazz.invoke("getDeclaredField").arg(key));

				// field.setInt(next, ref(id))
				block.body().add(_field.invoke("setInt").arg(_next).arg(JExpr.ref(key)));

				block._catch(ref(Exception.class)).body().directStatement("// nothing");
			}
		}

		// check method
		_method = cBuilder.method(JMod.PUBLIC, ctx.model.VOID, "_checkInvocations");

		if (methods.isEmpty()) {
			_method.body().directStatement("// nothing");
		} else {
			for (MethodOutline method : methods) {
				String key = "ic_"+makeMethodKey(outline, method);
				String message = "Expected at least "+method.minOccurrences+" invocations of method '"+method.getMethodSignature()+"'.";

				_method.body()._if(JExpr.ref(key).gt(JExpr.lit(0)))._then()
					._throw(JExpr._new(ref(ExpectedInvocationsException.class)).arg(message));
			}
		}
	}

	// --------------------------------------------------------------------------- //

	protected JType computeImplementationReturnType(Set<MethodOutline> allMethods, MethodOutline method) {
		JType returnType;

		if (method.getBlockChain().isEmpty() && method.isTerminal()) {
			if (method.getReturnType() == null) {
				Class blockReturnType = outline.getReturnType();

				if (blockReturnType != null) {
					if (Void.class.equals(blockReturnType)) {
						returnType = ctx.model.VOID;
					} else {
						returnType = ref(blockReturnType);
					}
				} else {
					returnType = ref(BuilderImplementation.class);
				}
			} else {
				returnType = ref(method.getReturnType());
			}
		} else {
			returnType = getDynamicReturnType(outline, allMethods, method).erasure();
		}

		return returnType;
	}

	protected JExpression computeInitialReturnValue(Set<MethodOutline> allMethods, MethodOutline method) {
		JExpression returnValue;

		// terminal method exits the class (eventually)
		if (method.isTerminal()) {
			if (method.getReturnType() == null) {
				returnValue = JExpr.ref(Constants.RETURN_VALUE_NAME);
			} else {
				returnValue = null;  // (means return the intermediate result)
			}

		// required method will return self
		} else if (method.isRequired()) {
			returnValue = JExpr._this();

		// dynamic method moves laterally to a sibling class
		} else {
			JType returnType = getSubsetImplementation(outline, computeMinusMethod(allMethods, method));

			returnValue =
				JExpr._new(returnType)
					.arg(JExpr.ref(Constants.HELPER_VALUE_NAME))
					.arg(JExpr.ref(Constants.RETURN_VALUE_NAME))
			;
		}

		return returnValue;
	}

	protected void addMethod(JDefinedClass cBuilder, JType returnType, JExpression initialReturnValue, MethodOutline method) {
		JMethod _method = addMethod(cBuilder, returnType, JMod.PUBLIC, method);

		// for every block chain, add a new object wrapper declaration
		List<JVar> helpers = new ArrayList<JVar>();
		for (BlockOutline blockChain : method.getBlockChain()) {
			helpers.add(addHelper(getHelperInterface(blockChain), helpers.size() + 1, _method));
		}

		// invocation tracking
		if (method.minOccurrences > 0) {
			_method.body().directStatement("--ic_"+makeMethodKey(outline, method)+";");
		}

		// invocation check before helper call
		if (method.isTerminal()  && !outline.isTerminal()) {
			JVar _cur = _method.body().decl(ref(BuilderImplementation.class), "cur", JExpr.ref(Constants.RETURN_VALUE_NAME));
			JWhileLoop _while = _method.body()._while(_cur.ne(JExpr._null()));
			_while.body().add(_cur.invoke("_checkInvocations"));
			_while.body().assign(_cur, _cur.invoke("_getParent"));
			_method.body().directStatement(" ");
		}

		// invoke the main helper
		String helperMethodName = new MethodParser(method.getMethodSignature()).methodName;
		JInvocation helperCall = makeHelperCall(_method, helperMethodName);

		// add the wrapped helpers as parameters
		for (JVar helper : helpers) {
			helperCall.arg(helper);
		}

		// TODO: This is horribly ugly and confusing, but it works damnit!

		// add to method body, capture result if needed
		if (method.isTerminal()) {

			// implicit, so call the special helper method
			if (method.isImplicitTerminal()) {
				_method.body().add(helperCall);
				initialReturnValue = JExpr.ref(Constants.RETURN_VALUE_NAME);

			// normal, so get return value from helper call
			} else {
				if (method.getReturnType() != null) {
					initialReturnValue
						= _method.body().decl(ref(method.getReturnType()), Constants.RESULT_VALUE_NAME, helperCall);
				} else {
					_method.body().add(helperCall);
				}
			}
		} else {
			_method.body().add(helperCall);
		}

		_method.body().directStatement(" ");
		JExpression returnValue = initialReturnValue;

		for (int i = method.getBlockChain().size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.getBlockChain().get(i);
			JDefinedClass cTargetBuilder = getTopLevelImplementation(targetBlock);

			returnValue = _method.body().decl(
				cTargetBuilder, "step" + (i + 1),
				JExpr._new(cTargetBuilder)
					.arg(helpers.get(i).invoke("get"))
					.arg(returnValue)
			);
		}

		// return value is stored if invocation tracking is required, but not blockChain present
		JExpression _retval;
		if ((returnValue != JExpr._this() && !method.isTerminal() && method.getBlockChain().isEmpty())){
			_retval = _method.body().decl(returnType, "retval", returnValue);
		} else {
			_retval = returnValue;
		}

		// No need to transfer if it is to ourselves!
		if (!method.isTerminal() && returnValue != JExpr._this() && !outline.isTerminal()) {
			_method.body().invoke("_transferInvocations").arg(_retval);
		}

		// return call
		_method.body()._return(_retval);
	}

	private JVar addHelper(JDefinedClass iHelper, int id, JMethod _method) {
		JType wrappedType = ref(ObjectWrapper.class).narrow(iHelper);
		JVar _helper = _method.body().decl(wrappedType, "helper"+id,JExpr._new(wrappedType));
		return _helper;
	}
}
