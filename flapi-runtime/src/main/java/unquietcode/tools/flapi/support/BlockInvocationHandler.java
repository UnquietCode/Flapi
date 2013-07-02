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

package unquietcode.tools.flapi.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 2013-06-09
 *
 * {@link InvocationHandler} which backs a single Flapi block.
 */
public class BlockInvocationHandler implements InvocationHandler {
	private final Map<String, Pair<Counter, String>> trackedMethods = new HashMap<String, Pair<Counter, String>>();
	private final Object returnValue;
	private final Object helper;

	public BlockInvocationHandler(Object helper, Object returnValue) {
		this.helper = helper;
		this.returnValue = returnValue;
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MethodInfo info = method.getAnnotation(MethodInfo.class);

		if (args == null) { args = new Object[0]; }
		Object[] newArgs = Arrays.copyOf(args, args.length + 1, Object[].class);
		newArgs[newArgs.length-1] = null;

		// invocation tracking
		trackMethod(method);

		// invocation checks
		if (info.checkInvocations()) {
			if (info.checkParentInvocations()) {
				_checkAllInvocations();
			} else {
				_checkInvocations();
			}
		}

		return invokeAndReturn(method, args, proxy, info);
	}

	private Object invokeAndReturn(Method method, Object[] originalArgs, Object proxy, MethodInfo info) {
		final int depth = info.chain().length;

		// create the new arguments and types arrays
		Object[] newArgs = new Object[originalArgs.length+depth];
		Class<?>[] originalTypes = method.getParameterTypes();
		Class[] newTypes = new Class[originalTypes.length+depth];

		for (int i=0; i < depth; ++i) {
			newArgs[originalArgs.length+i] = new AtomicReference();
			newTypes[originalTypes.length+i] = AtomicReference.class;
		}

		System.arraycopy(originalArgs, 0, newArgs, 0, originalArgs.length);
		System.arraycopy(originalTypes, 0, newTypes, 0, originalTypes.length);
		Method helperMethod;

		// find the helper method
		try {
			helperMethod = helper.getClass().getMethod(method.getName(), newTypes);
		} catch (NoSuchMethodException ex) {
			throw new IllegalStateException("internal error", ex);
		}

		// make accessible if not (debatable as to whether this is a good idea)
		if (!helperMethod.isAccessible()) {
			helperMethod.setAccessible(true);
		}

		// invoke method
		Object result;
		try {
			result = helperMethod.invoke(helper, newArgs);
		} catch (IllegalAccessException ex) {
			throw new IllegalStateException(ex);
		} catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof RuntimeException) {
				throw (RuntimeException) ex.getTargetException();
			} else {
				throw new RuntimeException(ex.getTargetException());
			}
		}

		Object _returnValue = computeReturnValue(method, proxy, info, depth, helperMethod, result);

		// unwrap helper results
		for (int i=depth-1; i >= 0; --i) {
			AtomicReference wrapper = (AtomicReference) newArgs[originalArgs.length+i];

			if (wrapper.get() == null) {
				throw new IllegalStateException("null helper provided for method "+method.getName());
			}

			BlockInvocationHandler handler = new BlockInvocationHandler(wrapper.get(), _returnValue);
			_returnValue = handler._proxy(info.chain()[i]);
		}

		return _returnValue;
	}

	private Object computeReturnValue(Method method, Object proxy, MethodInfo info, int depth, Method helperMethod, Object result) {
		Object _returnValue;

		switch (info.type()) {
			case Ascending: {
				_returnValue = returnValue;
			} break;

			case Lateral: {
				if (depth > 0) {
					LateralHint hint = method.getAnnotation(LateralHint.class);

					if (hint == null) {
						throw new IllegalStateException("internal error: missing type hint");
					}

					_returnValue = this._proxy(hint.next());
				} else {
					_returnValue = this._proxy(method.getReturnType());
				}

			} break;

			case Recursive: {
				_returnValue = proxy;
			} break;

			case Terminal: {
				if (helperMethod.getReturnType().equals(void.class)) {
					_returnValue = returnValue;
				} else {
					_returnValue = result;
				}

			} break;

			default: throw new IllegalStateException("internal error");
		}
		return _returnValue;
	}

	private void trackMethod(Method method) {
		Tracked annotation = method.getAnnotation(Tracked.class);
		if (annotation == null) { return; }
		trackedMethods.get(annotation.key()).one.decrementAndGet();
	}

	@SuppressWarnings("unchecked")
	public final <T> T _proxy(Class<?> clazz) {
		registerNewTrackedMethods(clazz);
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
	}

	private void registerNewTrackedMethods(Class<?> clazz) {
		for (Method method : clazz.getMethods()) {
			Tracked annotation = method.getAnnotation(Tracked.class);
			if (annotation == null) { continue; }

			if (!trackedMethods.containsKey(annotation.key())) {
				Counter counter = new Counter(annotation.atLeast());
				trackedMethods.put(annotation.key(), new Pair<Counter, String>(counter, method.getName()));
			}
		}
	}

	protected final void _checkInvocations() {
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

	protected final void _checkAllInvocations() {
		BlockInvocationHandler cur = this;

		while (cur != null) {
			cur._checkInvocations();

			if (cur.returnValue != null && cur.returnValue instanceof BlockInvocationHandler) {
				cur = (BlockInvocationHandler) cur.returnValue;
			} else {
				cur = null;
			}
		}
	}

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
