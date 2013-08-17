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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ben Fagin
 * @version 2013-07-02
 */
/* package */abstract class TrackingExecutionListener implements ExecutionListener {
	private final Map<String, Pair<Counter, String>> trackedMethods = new HashMap<String, Pair<Counter, String>>();

	@Override
	public void next(Method method, Object[] args) {
		final MethodInfo info = method.getAnnotation(MethodInfo.class);
		final boolean shouldCheckParentInvocations = info.type() == TransitionType.Terminal;
		final boolean shouldCheckInvocations
			= info.type() == TransitionType.Terminal || info.type() == TransitionType.Ascending;

		// invocation tracking
		trackMethod(method);

		// invocation checks
		if (shouldCheckInvocations) {
			if (shouldCheckParentInvocations) {
				checkAllInvocations();
			} else {
				checkInvocations();
			}
		}
	}

	public void registerNewTrackedMethods(Class<?> clazz) {
		for (Method method : clazz.getMethods()) {
			Tracked annotation = method.getAnnotation(Tracked.class);
			if (annotation == null) { continue; }

			if (!trackedMethods.containsKey(annotation.key())) {
				Counter counter = new Counter(annotation.atLeast());
				trackedMethods.put(annotation.key(), new Pair<Counter, String>(counter, method.getName()));
			}
		}
	}

	private void trackMethod(Method method) {
		Tracked annotation = method.getAnnotation(Tracked.class);
		if (annotation == null) { return; }
		trackedMethods.get(annotation.key()).one.decrementAndGet();
	}

	public void checkInvocations() {
		for (Map.Entry<String, Pair<Counter, String>> entry : trackedMethods.entrySet()) {
			if (entry.getValue().one.get() > 0) {
				throw new ExpectedInvocationsException(String.format(
					"Expected at least %s invocations of method '%s'.",
					entry.getValue().one.initial,
					entry.getValue().two
				));
			}
		}
	}

	public abstract void checkAllInvocations();

	private static class Pair<A, B> {
		public final A one;
		public final B two;

		public Pair(A one, B two) {
			this.one = one;
			this.two = two;
		}
	}

	private static class Counter extends AtomicInteger {
		public final int initial;

		public Counter(int initial) {
			super(initial);
			this.initial = initial;
		}
	}
}
