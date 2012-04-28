package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 04-25-2012
 *
 * Creates the base implementation for a given block.
 */
public class BlockGenerator_Subsets extends AbstractBlockGenerator<BlockOutline, Void> {

	public BlockGenerator_Subsets(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public Void generate() {
		for (Set<MethodOutline> combination : makeCombinations(outline.getDynamicMethods())) {

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getInterface(getGeneratedName(outline.getBaseInterface(), combination));
			iSubset.generify("_ReturnType");

			// make the class
			JDefinedClass cSubset = createSubsetImpl(combination);

			// add the required methods
			for (MethodOutline method : outline.getRequiredMethods()) {

				// add to interface
				addMethod(iSubset, getDynamicReturnType(outline, combination, method, true), JMod.NONE, method);

				// add to implementation
				JType returnType;
				if (method.getBlockChain().isEmpty()) {
					if (method.isTerminal()) {
						returnType = ref(Object.class);
					} else {
						returnType = iSubset.erasure();
					}
				} else {
					returnType = getInterface(method.getBlockChain().get(0).getTopLevelInterface());
				}

				JExpression initialReturnValue = method.isTerminal() ? JExpr.ref("_returnValue") : JExpr._this();
				addMethod(cSubset, returnType, initialReturnValue, method);
			}

			// add the dynamic methods
			for (MethodOutline method : combination) {
				JType returnType = getDynamicReturnType(outline, combination, method, true);

				// add to interface
				addMethod(iSubset, returnType, JMod.NONE, method);

				// add to implementation
				JType initialReturnType = getDynamicReturnType(outline, combination, method, false).erasure();
				JExpression initialReturnValue =
					JExpr._new(initialReturnType)
						.arg(JExpr.ref("_helper"))
						.arg(JExpr.ref("_returnValue"))
				;

				addMethod(cSubset, returnType.erasure(), initialReturnValue, method);
			}
		}

		return null;
	}

	private JDefinedClass createSubsetImpl(Set<MethodOutline> methodCombination) {
		JDefinedClass cSubset = getClass(getGeneratedName(outline.getBaseImplementation(), methodCombination));
		JDefinedClass iSubset = getInterface(getGeneratedName(outline.getBaseInterface(), methodCombination));
		cSubset._implements(iSubset);

		JFieldVar _helper = cSubset.field(JMod.PRIVATE+JMod.FINAL, getInterface(outline.getHelperInterface()), "_helper");
		JFieldVar _returnValue = cSubset.field(JMod.PRIVATE+JMod.FINAL, ref(Object.class), "_returnValue");

		JMethod constructor = cSubset.constructor(JMod.NONE);
		JVar pHelper = constructor.param(getInterface(outline.getHelperInterface()), "helper");
		JVar pReturnValue = constructor.param(ref(Object.class), "returnValue");

		constructor.body().assign(_helper, pHelper);
		constructor.body().assign(_returnValue, pReturnValue);

		return cSubset;
	}
}
