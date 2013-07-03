/*******************************************************************************
 Copyright 2013 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

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
