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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Modified from code found at http://www.javablogging.com/dynamic-in-memory-compilation
 * Many thanks to the author, Miron Sadziak.
 */
public class JavaClassObject extends SimpleJavaFileObject {
	private final File file;


	/**
	 * Registers the compiled class object under URI
	 * containing the class full name
	 *
	 * @param name
	 *            Full name of the compiled class
	 * @param kind
	 *            Kind of the data. It will be CLASS in our case
	 */
	public JavaClassObject(String name, Kind kind, File output) {
		super(URI.create("string:///" + name.replace('.', '/')
			+ kind.extension), kind);

		this.file = output;
	}

	/**
	 * Will provide the compiler with an output stream that leads
	 * to our byte array. This way the compiler will write everything
	 * into the byte array that we will instantiate later
	 */
	@Override
	public OutputStream openOutputStream() throws IOException {
		return new FileOutputStream(file);
	}
}