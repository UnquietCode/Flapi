package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

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
			// add the method to the interface (returning its self type)
			addMethod(iBuilder, iBuilder.typeParams()[0], JMod.NONE, method);

			// add the method to the base class (returning interface self type)
			JMethod m = addMethod(cBuilder, iBuilder.typeParams()[0], JMod.PUBLIC, method);
			m.body().add(makeHelperCall(m, method));

			// populate the helper with all of the methods in the block, returning void
			addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);
		}

		// -- iterative level --

		// create every builder interface and associated implementation
		for (Set<MethodOutline> combination : makeCombinations(block.getDynamicMethods())) {
			String generatedName = getGeneratedName(block.getBaseInterface(), combination);
			boolean isBase = combination.isEmpty();

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getInterface(generatedName);

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
}
