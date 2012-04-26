package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author Ben Fagin
 * @version 04-24-2012
 *
 * Creates the base interface for a given block.
 */
public class BlockGenerator_BaseInterface extends AbstractGenerator<BlockOutline, JDefinedClass> {

	public BlockGenerator_BaseInterface(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}

	@Override
	public JDefinedClass generate() {
		JDefinedClass iBuilder = getInterface(outline.getBaseInterface());
		iBuilder.generify("_ReturnType");

		// add required methods to base
		for (MethodOutline method : outline.getRequiredMethods()) {
			addMethod(iBuilder, getDynamicReturnType(outline, Collections.<MethodOutline>emptySet(), method, true), JMod.NONE, method);
		}

		return iBuilder;
	}

}
