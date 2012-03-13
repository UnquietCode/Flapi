package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.Pair;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin (Nokia)
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
		JDefinedClass cBuilder = getClass(block.getBaseImplementation());		
		
		// get the 'Helper' interface (user will implement this for behavior)
		JDefinedClass iHelper = getInterface(block.getHelperInterface());

		// for every required method, add the invocations
		for (MethodOutline method : block.getRequiredMethods()) {
			addMethods(method, iBuilder, cBuilder, iHelper);
		}
			
			
			

		// -- iterative level --

		// create every builder interface and associated implementation

		for (Set<MethodOutline> combination : makeCombinations(block.getDynamicMethods())) {
			String generatedName = getGeneratedName(block.getBaseInterface(), combination);
			boolean isBase = combination.isEmpty();

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getInterface(generatedName);

			// extend the base interface, return type of self (skip base)
			if (!combination.isEmpty()) {
				iSubset._implements(iBuilder.narrow(iSubset));
			}

			// TODO a clean way of getting the correct return types
			// add methods to interface
//			for (MethodOutline method : combination) {
//				JType returnType = getReturnType(block.blockName+"Builder", method, methods, true);
//
//				if (method.blockChain.isEmpty()) {
//					addMethod(iSubset, returnType, JMod.NONE, method);
//				} else {
//					addMethod(iSubset, )
//				}
//			}

			// make the Impl* class
//			JDefinedClass cSubset = generateImplClass(
//				generatedName,
//				isBase ? null : cBuilder, iSubset, iHelper,
//				combination, block
//			);
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
	
	/*
	    List<Object> helpers = _helper.doMethod(..);
        int i = helpers.size()-1;
        SomeBuilder<PreviousType> = new ImplSomeTime<PreviousType>((HelperType) helpers.get(--i, previousValue);
	 */
	private void addMethods(MethodOutline method, JDefinedClass iBuilder, JDefinedClass cBuilder, JDefinedClass iHelper) {
		// add the method to the interface (returning its self type)
		addMethod(iBuilder, iBuilder.typeParams()[0], JMod.NONE, method);

		// populate the helper with all of the methods in the block
		// return null when there is no block chain, but a list otherwise
		if (method.blockChain.isEmpty()) {
			addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);
		} else {
			addMethod(iHelper, ref(List.class).narrow(Object.class), JMod.NONE, method);
		}

		// --implementation--
		
		// add the method to the base class (returning interface self type)
		JMethod m = addMethod(cBuilder, iBuilder.typeParams()[0], JMod.PUBLIC, method);
		JInvocation helperInvocation = makeHelperCall(m, method);

		// if no chain, helper method is void
		if (method.blockChain.isEmpty()) {
			m.body().add(helperInvocation);

		// if chained, expect at least one helper back per chain
		} else {
			// List<Object> helpers = _helper.doMethod(..);
			JVar _helpers = m.body().decl(ref(List.class).narrow(Object.class), "helpers", helperInvocation);
	
			// nested invocations re-create the chain
			JClass previousType = iBuilder;
			JExpression previousValue = JExpr._this();
	
			for (int i=method.blockChain.size()-1; i >=0; --i) {
				BlockOutline targetBlock = method.blockChain.get(i);			
				JDefinedClass iTargetBuilder = getInterface(targetBlock.getTopLevelInterface());
				JDefinedClass cTargetBuilder = getClass(targetBlock.getTopLevelImplementation());
				JDefinedClass iTargetHelper = getInterface(targetBlock.getHelperInterface());
				JClass targetType = iTargetBuilder.narrow(previousType);
				JClass targetValue = cTargetBuilder.narrow(previousType);
	
				// SomeBuilder<PreviousType> = new ImplSomeTime<PreviousType>((HelperType) helpers.get(#), previousValue);
				JVar invocation = m.body().decl(targetType, "helper"+i,
					JExpr._new(targetValue)
						.arg(JExpr.cast(iTargetHelper, _helpers.invoke("get").arg(JExpr.lit(i))))
						.arg(previousValue))
				;
				
				previousType = targetType;
				previousValue = invocation;
			}
		}
	}
}
