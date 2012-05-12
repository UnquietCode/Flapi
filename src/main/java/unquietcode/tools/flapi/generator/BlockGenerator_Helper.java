package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

/**
 * @author Ben Fagin
 * @version 04-24-2012
 *
 * Creates the 'Helper' interface. Users will implement this for behavior.
 */
public class BlockGenerator_Helper extends AbstractGenerator<BlockOutline, JDefinedClass> {

	public BlockGenerator_Helper(BlockOutline outline, GeneratorContext context) {
		super(outline, context);
	}


	@Override
	public JDefinedClass generate() {
		JDefinedClass iHelper = getHelperInterface(outline);

		for (MethodOutline method : outline.methods) {
			JMethod _method = addMethod(iHelper, ctx.model.VOID, JMod.NONE, method);

			// for every block in the chain, add a wrapped helper parameter
			int i=1;
			for (BlockOutline block : method.getBlockChain()) {
				JDefinedClass blockHelper = getHelperInterface(block);
				_method.param(ref(ObjectWrapper.class).narrow(blockHelper), "_helper"+(i++));
			}
		}

		return iHelper;
	}
}
