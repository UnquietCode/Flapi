package unquietcode.tools.flapi.graph;

/**
 * @author Ben Fagin
 * @version 08-17-2012
 */
public interface GenericVisitor<T> {
	void visit(T object);
}
