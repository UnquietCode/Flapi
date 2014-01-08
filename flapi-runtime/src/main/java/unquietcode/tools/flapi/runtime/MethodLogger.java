package unquietcode.tools.flapi.runtime;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of {@link ExecutionListener} which keeps track
 * of the number of methods invoked, and prints the current method
 * plus its logical number. This is extremely useful for debugging
 * chained methods whose line number in a stack trace may not
 * correspond to the actual line number in the source code.
 *
 * @author Ben Fagin
 * @version 2013-07-02
 */
public abstract class MethodLogger implements ExecutionListener {
	private final AtomicInteger methodCounter = new AtomicInteger(1);

	public MethodLogger() { }


	/**
	 * Create a MethodLogger which prints information to a
	 * provided {@link PrintStream} instance, such as {@link System#out}.
	 *
	 * @param stream to print to
	 * @return a new MethodLogger, with initial line number set to 1
	 */
	public static MethodLogger from(final PrintStream stream) {
		return new MethodLogger() {
			public @Override void logMethod(String methodName, int methodNumber) {
				stream.println(methodNumber +" : "+ methodName);
			}
		};
	}

	@Override
	public final void next(Method method, Object[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(method.getName()).append("(");

		boolean first = true;
		for (Class<?> clazz : method.getParameterTypes()) {
			if (!first) { sb.append(", "); }
			else { first = false; }
			sb.append(clazz.getSimpleName());
		}

		sb.append(")");
		logMethod(sb.toString(), methodCounter.getAndIncrement());
	}

	public abstract void logMethod(String methodName, int methodNumber);
}
