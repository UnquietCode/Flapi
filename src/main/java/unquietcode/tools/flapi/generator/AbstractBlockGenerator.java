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
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.outline.Outline;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 04-24-2012
 */
public abstract class AbstractBlockGenerator<_From extends BlockOutline, _To> extends AbstractGenerator<_From, _To> {

	public AbstractBlockGenerator(_From outline, GeneratorContext context) {
		super(outline, context);
	}

	protected JType computeImplementationReturnType(JDefinedClass iBuilder, Set<MethodOutline> allMethods, MethodOutline method) {
		JType returnType;

		if (method.getBlockChain().isEmpty() && method.isTerminal()) {
			returnType = ref(Object.class);
		} else {
			returnType = getDynamicReturnType(outline, allMethods, method).erasure();
		}

		return returnType;
	}

	protected JExpression computeInitialReturnValue(Set<MethodOutline> allMethods, MethodOutline method) {
		JExpression returnValue;

		// required method will return self
		if (method.isRequired()) {
			returnValue = JExpr._this();

		// terminal method exits the class (eventually)
		} else if (method.isTerminal()) {
			returnValue = JExpr.ref("_returnValue");

		// dynamic method moves laterally to a sibling class
		} else {
			JType returnType = getSubsetImplementation(outline, computeMinusMethod(allMethods, method));

			returnValue =
				JExpr._new(returnType)
					.arg(JExpr.ref("_helper"))
					.arg(JExpr.ref("_returnValue"))
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

		// invocation check before helper call
		if (method.isTerminal()) {
			_method.body().invoke("_checkInvocations");
		}

		// invoke the main helper
		JInvocation helperCall = makeHelperCall(_method, method);

		// add the wrapped helpers as parameters
		for (JVar helper : helpers) {
			helperCall.arg(helper);
		}

		// add to method body
		_method.body().add(helperCall);
		_method.body().directStatement(" ");

		JExpression returnValue = initialReturnValue;

		for (int i = method.getBlockChain().size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.getBlockChain().get(i);
			JDefinedClass iTargetBuilder = getTopLevelInterface(targetBlock);
			JDefinedClass cTargetBuilder = getTopLevelImplementation(targetBlock);

			JVar invocation = _method.body().decl(
				iTargetBuilder, "step" + (i + 1),
				JExpr._new(cTargetBuilder)
					.arg(helpers.get(i).invoke("get"))
					.arg(returnValue)
			);

			returnValue = invocation;
		}

		JExpression _retval = _method.body().decl(returnType, "retval", returnValue);

		// TODO optimization to avoid unecessary variable declaration
//		JExpression _retval = returnValue != JExpr._this()
//				? _method.body().decl(returnType, "retval", returnValue)
//				: returnValue;

		// invocation tracking
		if (method.minOccurrences > 0) {
			_method.body().directStatement("--ic_"+makeMethodKey(outline, method)+";"); //TODO: Is there a proper way?
		}

		// No need to transfer if it is to ourself!
		if (!method.isTerminal() && returnValue != JExpr._this()) {
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
