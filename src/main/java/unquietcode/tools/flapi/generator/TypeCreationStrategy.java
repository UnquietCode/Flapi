package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JDefinedClass;
import unquietcode.tools.flapi.graph.components.StateClass;

/**
 * @author Ben Fagin
 * @version 08-06-2012
 */
public interface TypeCreationStrategy {
	JDefinedClass createType(GeneratorContext ctx, StateClass state);
}
