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

package unquietcode.tools.flapi.plugin.compile;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Modified from code found at http://www.javablogging.com/dynamic-in-memory-compilation
 * Many thanks to the author, Miron Sadziak.
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

	/**
	 * CharSequence representing the source code to be compiled
	 */
	private CharSequence content;

	/**
	 * This constructor will store the source code in the
	 * internal "content" variable and register it as a
	 * source code, using a URI containing the class full name
	 *
	 * @param className
	 *            name of the public class in the source code
	 * @param content
	 *            source code to compile
	 */
	public CharSequenceJavaFileObject(String className,CharSequence content) {
		super(URI.create("string:///" + className.replace('.', '/')
			+ Kind.SOURCE.extension), Kind.SOURCE);
		this.content = content;
	}

	/**
	 * Answers the CharSequence to be compiled. It will give
	 * the source code stored in variable "content"
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return content;
	}
}