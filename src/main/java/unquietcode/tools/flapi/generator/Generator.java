package unquietcode.tools.flapi.generator;


import unquietcode.tools.flapi.outline.Outline;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public interface Generator<T extends Outline, _OutType> {
	
	_OutType generate();
	
}
