/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JClass;
import unquietcode.tools.flapi.graph.components.StateClass;

/**
 * @author Ben Fagin
 * @version 2014-08-03
 */
public abstract class DefaultTypeCreationStrategy implements TypeCreationStrategy {

	@Override
	public final JClass createWeakType(GeneratorContext ctx, StateClass state) {
		return createStrongType(ctx, state);
	}
}