package unquietcode.tools.flapi.runtime;

import java.lang.reflect.Method;

/**
 * Listen and respond to executions of methods in a builder.
 *
 * @author Ben Fagin
 * @version 2013-07-02
 */
public interface ExecutionListener {

	/**
	 * Respond to the next method about to be invoked.
	 * @param method the current interface method being invoked
	 * @param args  the current args to the method
	 */
	void next(Method method, Object[] args);
}
