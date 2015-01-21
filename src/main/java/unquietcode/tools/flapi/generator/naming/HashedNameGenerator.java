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
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * Name generator implementation which shortens method names into
 * smaller strings, and then hashes the entire class name using
 * an MD5 hasher. This has the added advantage that, since it is a
 * consistent hash, if there are no differences between names then
 * the hash will always be the same.
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
public class HashedNameGenerator extends DefaultNameGenerator {
	private final Function<String, String> methodNameHasher = new Hasher("m");

	private final Function<String, String> stateNameHasher = new Function<String, String>() {
		public @Override String apply(String string) {
			String hashed = Hashing.md5().hashString(string, Charset.forName("UTF-8")).toString();
			return "S"+hashed;
		}
	};

	@Override
	public String methodName(String methodKey) {
		return methodNameHasher.apply(methodKey);
	}

	@Override
	public String className(String stateKey) {
		return stateNameHasher.apply(stateKey);
	}
}