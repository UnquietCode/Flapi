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

package unquietcode.tools.flapi.graph;

import unquietcode.tools.flapi.graph.components.Transition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author Ben Fagin
* @version 2013-06-29
*/
public class BlockMethodTracker {
	private final Map<String, Set<String>> seenMethods = new HashMap<>();
	private final boolean useMethodKey;

	public BlockMethodTracker() {
		this(false);
	}

	public BlockMethodTracker(boolean useMethodKey) {
		this.useMethodKey = useMethodKey;
	}

	public boolean seen(Transition transition) {
		final String name = transition.getOwner().getName();
		final String key = useMethodKey
						 ? transition.info().keyString()
						 : transition.getMethodSignature().toString();

		Set<String> methods = seenMethods.get(name);

		if (methods == null) {
			methods = new HashSet<>();
			seenMethods.put(name, methods);
		}

		if (methods.contains(key)) {
			return true;
		} else {
			methods.add(key);
			return false;
		}
	}
}