/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

/**
 * Classes which wish to make use of reflective tools
 * should implement this to signal that they can
 * return a valid descriptor. Implementations should
 * probably also guarantee a no-args constructor is
 * available.
 *
 * @author Ben Fagin
 * @version 2013-07-05
 */
public interface DescriptorMaker {
	Descriptor descriptor();
}
