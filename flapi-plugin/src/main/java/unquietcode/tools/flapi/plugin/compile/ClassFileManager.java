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

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;

/**
 * Modified from code found at http://www.javablogging.com/dynamic-in-memory-compilation
 * Many thanks to the author, Miron Sadziak.
 */
public class ClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
	private final String basePath;


	/**
	 * Will initialize the manager with the specified
	 * standard java file manager
	 */
	public ClassFileManager(StandardJavaFileManager standardManager, String basepath) {
		super(standardManager);
		this.basePath = basepath;
	}

	/**
	 * Gives the compiler an instance of the JavaClassObject
	 * so that the compiler can write the byte code into it.
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(
		Location location, String className, JavaFileObject.Kind kind, FileObject sibling
	) throws IOException {
		File file = new File(basePath+File.separator+className.replace('.', '/')+kind.extension);
		file.getParentFile().mkdirs();
		file.createNewFile();

		return new JavaClassObject(className, kind, file);
	}
}