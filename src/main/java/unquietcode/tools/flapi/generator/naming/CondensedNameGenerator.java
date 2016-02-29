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


import java.util.function.Function;

/**
 * Name generator implementation which shortens
 * method names into a smaller string, while
 * leaving class names unaltered.
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
public class CondensedNameGenerator extends DefaultNameGenerator {
	private final Function<String, String> methodNameHasher = new Hasher("m");

	@Override
	public String methodName(String methodKey) {
		return methodNameHasher.apply(methodKey);
	}
}