package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.MinimumInvocationsException;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.lang.reflect.Field;
import java.util.*;

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
			addInvocationTracking(cSubset, outline.methods);

			// add the required methods
			for (MethodOutline method : outline.getRequiredMethods()) {

				// add to interface
				addMethod(iSubset, getDynamicReturnType(outline, combination, method), JMod.NONE, method);

				// add to implementation
				JExpression initialReturnValue = method.isTerminal() ? JExpr.ref("_returnValue") : JExpr._this();
				addMethod(cSubset, computeImplementationReturnType(iSubset, combination, method), initialReturnValue, method);
			}

			// add the dynamic methods
			for (MethodOutline method : combination) {
				JType returnType = getDynamicReturnType(outline, combination, method);

				// add to interface
				addMethod(iSubset, returnType, JMod.NONE, method);

				// add to implementation
				addMethod(
					cSubset,
					computeImplementationReturnType(iSubset, combination, method),
					computeInitialReturnValue(combination, method),
					method
				);
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

		// constructor
		JMethod constructor = cSubset.constructor(JMod.NONE);
		JVar pHelper = constructor.param(getInterface(outline.getHelperInterface()), "helper");
		JVar pReturnValue = constructor.param(ref(Object.class), "returnValue");

		constructor.body().assign(_helper, pHelper);
		constructor.body().assign(_returnValue, pReturnValue);

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
		_method = cBuilder.method(JMod.PRIVATE, ctx.model.VOID, "_checkInvocations");

		if (methods.isEmpty()) {
			_method.body().directStatement("// nothing");
		} else {
			for (MethodOutline method : methods) {
				String key = "ic_"+makeMethodKey(outline, method);
				String message = "Expected at least "+method.minOccurrences+" invocations of method '"+method.methodSignature+"'.";

				_method.body()._if(JExpr.ref(key).gt(JExpr.lit(0)))._then()
					._throw(JExpr._new(ref(MinimumInvocationsException.class)).arg(message));
			}
		}
	}
}
