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

	protected void addMethod(JDefinedClass cBuilder, JType returnType, JExpression initialReturnValue, MethodOutline method) {
		JMethod _method = addMethod(cBuilder, returnType, JMod.PUBLIC, method);

		// for every block chain, add a new object wrapper declaration
		List<JVar> helpers = new ArrayList<JVar>();
		for (BlockOutline blockChain : method.getBlockChain()) {
			helpers.add(addHelper(getInterface(blockChain.getHelperInterface()), helpers.size() + 1, _method));
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

		// build the return type, using the values of the helpers populated
		JExpression returnValue = initialReturnValue;

		for (int i = method.getBlockChain().size()-1; i >=0; --i) {
			BlockOutline targetBlock = method.getBlockChain().get(i);
			JDefinedClass iTargetBuilder = getInterface(targetBlock.getTopLevelInterface());
			JDefinedClass cTargetBuilder = getClass(targetBlock.getTopLevelImplementation());

			returnType = iTargetBuilder.narrow(returnType);

			JVar invocation = _method.body().decl(
				iTargetBuilder, "step" + (i + 1),
				JExpr._new(cTargetBuilder)
					.arg(helpers.get(i).invoke("get"))
					.arg(returnValue)
			);

			returnValue = invocation;
		}

		// return call
		_method.body()._return(returnValue);
	}

	private JVar addHelper(JDefinedClass iHelper, int id, JMethod _method) {
		JType wrappedType = ref(ObjectWrapper.class).narrow(iHelper);
		JVar _helper = _method.body().decl(wrappedType, "helper"+id,JExpr._new(wrappedType));
		return _helper;
	}

}
