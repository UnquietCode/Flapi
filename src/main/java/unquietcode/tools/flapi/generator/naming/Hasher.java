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

package unquietcode.tools.flapi.generator.naming;


import javax.lang.model.SourceVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Helper class which can internally keep track of hashed
 * names and a mapping from their original value.
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
/* package */ class Hasher implements Function<String, String> {
	private Map<String, String> nameMap = new HashMap<>();
	private int nameIdCounter = 1;
	private final String prefix;

	public Hasher(String prefix) {
		this.prefix = Objects.requireNonNull(prefix);
		checkArgument(SourceVersion.isIdentifier(prefix));
	}

	@Override
	public String apply(String string) {
		if (nameMap.containsKey(string)) {
			return nameMap.get(string);
		} else {
			String gen = prefix + (nameIdCounter++);
			nameMap.put(string, gen);
			return gen;
		}
	}
}