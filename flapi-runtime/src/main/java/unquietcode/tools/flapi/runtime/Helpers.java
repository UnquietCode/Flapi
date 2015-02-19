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
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class Helpers {
	
	private Helpers() { }

	@SuppressWarnings("unchecked")
	public static <T> T beanProxyHelper(Class<T> helper, final Object bean) {
		if (!helper.isInterface()) {
			throw new IllegalArgumentException("only helper interfaces are allowed");
		}

		return (T) Proxy.newProxyInstance(helper.getClassLoader(), new Class[]{helper}, new InvocationHandler() {
			private final Map<String, Method> methodMap = new HashMap<String, Method>();

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				OriginalMethod originalMethod = method.getAnnotation(OriginalMethod.class);
				final String actualMethodName;

				// is it a transformed method?
				if (originalMethod != null) {

					// check if it is the final generated method first
					if (originalMethod.value().equals(OriginalMethod.DEFAULT_NONE)) {
						return bean;
					}

					// use the original method name
					actualMethodName = originalMethod.value();
				} else {

					// use the current method name by default
					actualMethodName = method.getName();
				}

				// look up the method
				Method actualMethod = getMethod(actualMethodName, method.getParameterTypes());
				return actualMethod.invoke(bean, args);
			}

			private Method getMethod(String methodName, Class[] parameterTypes) {
				Method actualMethod = methodMap.get(methodName);

				if (actualMethod != null) {
					return actualMethod;
				}

				// find the method
				try {
					actualMethod = bean.getClass().getMethod(methodName, parameterTypes);
				} catch (NoSuchMethodException ex) {

					// fail
					throw new IllegalStateException("method not found");
				}

				methodMap.put(methodName, actualMethod);
				return actualMethod;
			}
		});
	}

	public interface Invoker<In> {
		void call(AtomicReference<In> in);
	}

	public static <T> T invoke(Invoker<T> invoker) {
		AtomicReference<T> reference = new AtomicReference<>();

		try {
			invoker.call(reference);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		return reference.get();
	}
}