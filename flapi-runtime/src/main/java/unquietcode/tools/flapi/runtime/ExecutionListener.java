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
