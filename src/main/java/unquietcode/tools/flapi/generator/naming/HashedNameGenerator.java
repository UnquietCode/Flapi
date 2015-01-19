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

import com.google.common.base.Function;

/**
 * Name generator implementation which shortens names into
 * smaller strings.
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
public class HashedNameGenerator extends DefaultNameGenerator {
	private final Function<String, String> methodNameHasher = new Hasher("m");
	private final Function<String, String> stateNameHasher = new Hasher("S");

	@Override
	public String methodName(String methodKey) {
		return methodNameHasher.apply(methodKey);
	}

	@Override
	public String className(String stateKey) {
		return stateNameHasher.apply(stateKey);
	}
}