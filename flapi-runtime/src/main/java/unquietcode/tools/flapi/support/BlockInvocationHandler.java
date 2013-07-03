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

import unquietcode.tools.flapi.runtime.ExecutionListener;
import unquietcode.tools.flapi.runtime.TrackingExecutionListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 2013-06-09
 *
 * {@link InvocationHandler} which backs a single Flapi block.
 * Each handler keeps track of invocations to methods which require
 * atLeast tracking. The other function is to maintain the return
 * value for the preceding block, or an intermediate terminal value.
 */
public class BlockInvocationHandler implements InvocationHandler {
	private final Set<ExecutionListener> listeners;
	private final TrackingExecutionListener trackingListener = new Tracker();
	private final Object returnValue;
	private final Object helper;

	public BlockInvocationHandler(Object helper, Object returnValue) {
		this(
			helper, returnValue,
			Collections.newSetFromMap(new IdentityHashMap<ExecutionListener, Boolean>())
		);
	}

	private BlockInvocationHandler(
		Object helper, Object returnValue,
		Set<ExecutionListener> listeners
	){
		this.helper = helper;
		this.returnValue = returnValue;
		this.listeners = listeners;
	}

	public void addListeners(ExecutionListener...listeners) {
		if (listeners != null) {
			this.listeners.addAll(Arrays.asList(listeners));
		}
	}

	public void addListeners(Set<ExecutionListener> listener) {
		listeners.addAll(listener);
	}

	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (args == null) { args = new Object[0]; }
		MethodInfo info = method.getAnnotation(MethodInfo.class);

		// atLeast tracking
		trackingListener.next(method, args);

		for (ExecutionListener listener : listeners) {
			listener.next(method, args);
		}

		// do the actual work
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

			BlockInvocationHandler handler
				= new BlockInvocationHandler(wrapper.get(), _returnValue, listeners);

			_returnValue = handler._proxy(info.chain()[i]);
		}

		return _returnValue;
	}

	private Object computeReturnValue(Method method, Object proxy, MethodInfo info, int depth, Method helperMethod, Object result) {
		switch (info.type()) {
			case Ascending: {
				return returnValue;
			}

			case Lateral: {
				if (depth > 0) {
					if (info.next() == MethodInfo.class) {
						throw new IllegalStateException("internal error: missing type hint");
					}

					return this._proxy(info.next());
				} else {
					return this._proxy(method.getReturnType());
				}
			}

			case Recursive: {
				return proxy;
			}

			case Terminal: {
				if (helperMethod.getReturnType().equals(void.class)) {
					return returnValue;
				} else {
					return result;
				}
			}

			default: throw new IllegalStateException("internal error");
		}
	}

	@SuppressWarnings("unchecked")
	public final <T> T _proxy(Class<?> clazz) {
		trackingListener.registerNewTrackedMethods(clazz);
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
	}

	private class Tracker extends TrackingExecutionListener {
		public @Override void checkAllInvocations() {
			BlockInvocationHandler cur = BlockInvocationHandler.this;

			while (cur != null) {
				cur.trackingListener.checkInvocations();

				if (cur.returnValue != null && cur.returnValue instanceof BlockInvocationHandler) {
					cur = (BlockInvocationHandler) cur.returnValue;
				} else {
					cur = null;
				}
			}
		}
	}
}
