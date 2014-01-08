package unquietcode.tools.flapi.support;

/**
 * @author Ben Fagin
 * @version 05-31-2012
 *
 * Unifying interface for all builder implementations, providing several
 * guarantees which are used within the generated logic.
 */
public interface BuilderImplementation {
	BuilderImplementation _getParent();
	void _checkInvocations();
}
