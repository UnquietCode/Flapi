package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.Pair;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-11-2012
 */
public class BlockGenerator extends AbstractGenerator<BlockOutline, Void> {

	public BlockGenerator(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}


	@Override
	public Void generate() {
		final BlockOutline block = outline;
		
		// -- base level --

			// get the base 'Builder' interface
			JDefinedClass iBuilder = getInterface(block.getBaseInterface());
			iBuilder.generify("_ReturnType");

			// get the base 'Builder' implementation
			JDefinedClass cBuilder = createBaseImpl();

			// get the 'Helper' interface (user will implement this for behavior)
			JDefinedClass iHelper = getInterface(block.getHelperInterface());

			// for every required method, add the invocations to the base classes
			for (MethodOutline method : block.getRequiredMethods()) {
				addMethods(method, iBuilder, cBuilder, iHelper);
			}

		// -------------- //

		// -- iterative level --

			// create every builder interface and associated implementation

			for (Set<MethodOutline> combination : makeCombinations(block.getDynamicMethods())) {

				// make the interface (the empty one should be the only already created one)
				JDefinedClass iSubset = getInterface(getGeneratedName(block.getBaseInterface(), combination));
				iSubset.generify("_ReturnType");

				// make the class
				JDefinedClass cSubset = createSubsetImpl(combination);

				// add the required methods from before to interface
				for (MethodOutline method : block.getRequiredMethods()) {
					addMethod(iSubset, getDynamicReturnType(block, combination, method, true), JMod.NONE, method);
				}

				// add the current working methods
				for (MethodOutline method : combination) {

					// add to interface
					JClass returnType = getDynamicReturnType(block, combination, method, true);
					addMethod(iSubset, returnType, JMod.NONE, method);

					// add to base
					JMethod m = addMethod(cSubset, getDynamicReturnType(block, combination, method, false), JMod.NONE, method);
					// TODO method body
				}
			}

		// -- nested level --

		for (BlockOutline child : block.blocks) {
			// if child already exists, then this is an error!
			// TODO throw exception

			// resolve block references (error if not found)
			// if there is a 'first' block or 'first' block reference (error if more than one)
				// add the method in such a way that it returns the nested element
				// remove it from the blocks pile
			// for all other blocks, process as nested


			//addNestedBlock(block, child, iBuilder, cBuilder);
		}

		return null;

	}

	private JDefinedClass createSubsetImpl(Set<MethodOutline> methodCombination) {
		JDefinedClass cSubset = getClass(getGeneratedName(outline.getBaseImplementation(), methodCombination));
		JDefinedClass iSubset = getInterface(getGeneratedName(outline.getBaseInterface(), methodCombination));

		// if not the base then extend from it and create the constructor
		if (!methodCombination.isEmpty()) {
			JDefinedClass baseImpl = getClass(outline.getBaseImplementation());
			cSubset._extends(baseImpl);
		}

		// always implement the interface
		cSubset._implements(iSubset);

		JMethod constructor = cSubset.constructor(JMod.NONE);
		JVar helper = constructor.param(getInterface(outline.getHelperInterface()), "helper");
		JVar returnValue = constructor.param(ctx.model.ref(Object.class), "returnValue");
		constructor.body().invoke("super").arg(helper).arg(returnValue);

		return cSubset;
	}

	private JDefinedClass createBaseImpl() {
		JDefinedClass builder = getClass(outline.getBaseImplementation());
		JFieldVar _helper = builder.field(JMod.PROTECTED+JMod.FINAL, getInterface(outline.getHelperInterface()), "_helper");
		JFieldVar _returnValue = builder.field(JMod.PROTECTED+JMod.FINAL, ctx.model.ref(Object.class), "_returnValue");

		JMethod constructor = builder.constructor(JMod.NONE);
		JVar helper = constructor.param(getInterface(outline.getHelperInterface()), "helper");
		JVar returnValue = constructor.param(ctx.model.ref(Object.class), "returnValue");

		constructor.body().assign(_helper, helper);
		constructor.body().assign(_returnValue, returnValue);

		return builder;
	}

	private void addMethods(MethodOutline method, JDefinedClass iBuilder, JDefinedClass cBuilder, JDefinedClass iHelper) {
		if (method.blockChain.isEmpty()) {
			// add the method to the interface
			if (method.isTerminal()) {
				addMethod(iBuilder, iBuilder.typeParams()[0], JMod.NONE, method);
			} else {
				addMethod(iBuilder, iBuilder.narrow(iBuilder.typeParams()[0]), JMod.NONE, method);
			}

			// add to the helper
			addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);

			// add to the base class
			JMethod m = addMethod(cBuilder, iBuilder, JMod.PUBLIC, method);
			JInvocation helperInvocation = makeHelperCall(m, method);
			m.body().add(helperInvocation);
			m.body()._return(JExpr._this());

		} else {
			JType previousType = method.isTerminal()
							   ? iBuilder.typeParams()[0]
							   : iBuilder.narrow(iBuilder.typeParams()[0]);
			JExpression previousValue = JExpr._this();

			// add to the base class
			JDefinedClass baseReturnType = getInterface(method.blockChain.get(0).getTopLevelInterface());
			JMethod m = addMethod(cBuilder, baseReturnType, JMod.PUBLIC, method);
			JInvocation helperInvocation = makeHelperCall(m, method);
			JVar _helpers = m.body().decl(ref(List.class).narrow(Object.class), "helpers", helperInvocation);

			for (int i = method.blockChain.size()-1; i >=0; --i) {
				BlockOutline targetBlock = method.blockChain.get(i);
				JDefinedClass iTargetBuilder = getInterface(targetBlock.getTopLevelInterface());
				JDefinedClass cTargetBuilder = getClass(targetBlock.getTopLevelImplementation());
				JDefinedClass iTargetHelper = getInterface(targetBlock.getHelperInterface());

				previousType = iTargetBuilder.narrow(previousType);

				// SomeBuilder<PreviousType> = new ImplSomeTime<PreviousType>((HelperType) helpers.get(#), previousValue);
				JVar invocation = m.body().decl(iTargetBuilder, "step"+i,
					JExpr._new(cTargetBuilder)
						.arg(JExpr.cast(iTargetHelper, _helpers.invoke("get").arg(JExpr.lit(i))))
						.arg(previousValue)
				);
			}

			// final return for base
			m.body()._return(JExpr.ref("step0"));

			// add to the interface
			addMethod(iBuilder, previousType, JMod.NONE, method);

			// add to the helper
			//   List<Object> helpers = _helper.doMethod(..);
			addMethod(iHelper, ref(List.class).narrow(Object.class), JMod.NONE, method);
		}
	}
}
