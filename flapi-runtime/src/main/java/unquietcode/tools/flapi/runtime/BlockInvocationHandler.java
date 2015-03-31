/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.runtime;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
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

	private Object invokeEnumSelector(Method method, Object[] originalArgs, final Object originalProxy, final MethodInfo info) {
		final Method helperMethod = SpringMethodUtils.findMethod(helper.getClass(), method.getName(), method.getParameterTypes());
		final Class<? extends Enum> enumType = method.getAnnotation(EnumSelectorHint.class).value();

		@SuppressWarnings("unchecked")
		final Consumer<Enum<?>> consumer = (Consumer<Enum<?>>) invokeMethod(helperMethod, Arrays.asList(originalArgs));

		if (consumer == null) {
			throw new IllegalStateException("consumer instance cannot be null");
		}

		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{method.getReturnType()}, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				// lookup enum by method name
				Enum<?> enumValue = Enum.valueOf(enumType, method.getName());

				// accept
				consumer.accept(enumValue);

				// proceed
				return computeReturnValue(method, originalProxy, info, info.chainInfo().length, helperMethod, consumer);
			}
		});
	}

	private Object invokeAndReturn(Method method, Object[] originalArgs, Object proxy, MethodInfo info) {

		// handle enum selectors specially
		if (method.getAnnotation(EnumSelectorHint.class) != null) {
			return invokeEnumSelector(method, originalArgs, proxy, info);
		}

		final ChainInfo[] chain = info.chainInfo();
		final int depth = chain.length;

		// create the new arguments and types arrays
		Class<?>[] originalTypes = method.getParameterTypes();
		List<Class<?>> newTypes = new ArrayList<>(Arrays.asList(originalTypes));
		List<Object> newArgs = new ArrayList<>(Arrays.asList(originalArgs));

		for (ChainInfo chainInfo : chain) {
			newArgs.add(chainInfo.position(), new AtomicReference());
			newTypes.add(chainInfo.position(), AtomicReference.class);
		}

		Method helperMethod;

		// find the helper method
		final Class<?>[] newTypesArray = newTypes.toArray(new Class[newTypes.size()]);
		helperMethod = SpringMethodUtils.findMethod(helper.getClass(), method.getName(), newTypesArray);

		if (helperMethod == null) {
			throw new IllegalStateException("unable to locate method '"+method.getName()+"' on helper");
		}

		// invoke the method
		Object result = invokeMethod(helperMethod, newArgs);
		Object _returnValue = computeReturnValue(method, proxy, info, depth, helperMethod, result);

		// unwrap helper results
		for (int i = chain.length-1; i >= 0; --i) {
			ChainInfo chainInfo = chain[i];
			AtomicReference wrapper = (AtomicReference) newArgs.get(chainInfo.position());

			if (wrapper.get() == null) {
				throw new IllegalStateException("null helper provided for method " + method.getName());
			}

			BlockInvocationHandler handler
				= new BlockInvocationHandler(wrapper.get(), _returnValue, listeners);

			_returnValue = handler._proxy(chainInfo.type());
		}

		return _returnValue;
	}

	private Object invokeMethod(Method helperMethod, List<Object> args) {

		// make accessible if not (debatable as to whether this is a good idea)
		if (!helperMethod.isAccessible()) {
			helperMethod.setAccessible(true);
		}

		final Object[] argsArray = args.toArray(new Object[args.size()]);

		// invoke method
		Object result;
		try {
			result = helperMethod.invoke(helper, argsArray);
		} catch (IllegalAccessException ex) {
			throw new IllegalStateException(ex);
		} catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof RuntimeException) {
				throw (RuntimeException) ex.getTargetException();
			} else {
				throw new RuntimeException(ex.getTargetException());
			}
		}
		return result;
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