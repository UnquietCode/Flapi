package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
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

			// skip the base, since it's created elsewhere
			if (combination.isEmpty()) {
				continue;
			}

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getInterface(getGeneratedName(outline.getBaseInterface(), combination));
			iSubset.generify("_ReturnType");

			// make the class
			JDefinedClass cSubset = createSubsetImpl(combination);

			// add the required methods (in the base) to interface
			for (MethodOutline method : outline.getRequiredMethods()) {
				addMethod(iSubset, getDynamicReturnType(outline, combination, method, true), JMod.NONE, method);
			}

			// add the dynamic methods
			for (MethodOutline method : combination) {
				JType returnType = getDynamicReturnType(outline, combination, method, true);

				// add to interface
				addMethod(iSubset, returnType, JMod.NONE, method);

				// add to implementation

				// this is the last method, and the last instance of it
				JExpression initialReturnValue;
				if (combination.size() == 1 && method.maxOccurrences == 1) {
					initialReturnValue = JExpr._this();
				} else {
					JType initialReturnType = getDynamicReturnType(outline, combination, method, false).erasure();
					initialReturnValue =
						JExpr._new(initialReturnType)
							.arg(JExpr.ref("_helper"))
							.arg(JExpr.ref("_returnValue"));
				}

				addMethod(cSubset, returnType.erasure(), initialReturnValue, method);
			}
		}

		return null;
	}

	private JDefinedClass createSubsetImpl(Set<MethodOutline> methodCombination) {
		JDefinedClass cSubset = getClass(getGeneratedName(outline.getBaseImplementation(), methodCombination));
		JDefinedClass iSubset = getInterface(getGeneratedName(outline.getBaseInterface(), methodCombination));

		// if empty, then this is the base which we already made
		if (methodCombination.isEmpty()) {
			return cSubset;
		}

		JDefinedClass baseImpl = getClass(outline.getBaseImplementation());
		cSubset._extends(baseImpl);
		cSubset._implements(iSubset);

		JMethod constructor = cSubset.constructor(JMod.NONE);
		JVar helper = constructor.param(getInterface(outline.getHelperInterface()), "helper");
		JVar returnValue = constructor.param(ref(Object.class), "returnValue");
		constructor.body().invoke("super").arg(helper).arg(returnValue);

		return cSubset;
	}
}
