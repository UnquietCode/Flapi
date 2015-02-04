/*********************************************************************
 Copyright 2015 the Flapi authors

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

package unquietcode.tools.flapi;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

import java.util.*;

public final class Utilities {
	
	private Utilities() { }


	// TODO use JDK8 classes

	public static <T> void safeRecurse(T first, Function<T, Collection<T>> function) {
		safeRecurse(Arrays.asList(first), function);
	}

	public static <T> void safeRecurse(Collection<T> first, Function<T, Collection<T>> function) {
		safeRecurse(Sets.<T>newIdentityHashSet(), first, function);
	}

	private static <T> void safeRecurse(Set<T> seen, Collection<T> first, Function<T, Collection<T>> function) {
		List<Collection<T>> collections = new ArrayList<>();
		collections.add(first);

		while (!collections.isEmpty()) {
			Collection<T> collection = collections.remove(0);
			if (collection == null || collection.isEmpty()) { continue; }

			for (T e : collection) {
				if (seen.contains(e)) { continue; }
				else { seen.add(e); }

				Collection<T> next = function.apply(e);
				if (next != null) { collections.add(next); }
			}
		}
	}
}