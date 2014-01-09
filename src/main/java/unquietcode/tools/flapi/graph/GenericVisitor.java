/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph;

/**
 * @author Ben Fagin
 * @version 08-17-2012
 */
public interface GenericVisitor<T> {
	void visit(T object);
}
