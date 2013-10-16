/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi;

import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Ben Fagin
 * @version 09-01-2012
 *
 * Test harness which will dynamically compile the resulting code model and ensure that
 * no errors occur. Then actions can be performed on the resulting loaded classes.
 */
public abstract class AbstractCompiledTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@After
	public void cleanup() {
		for (File file : temp.getRoot().listFiles()) {
			recursiveDelete(file);
		}
	}

	protected void testCompile() {
		List<File> sourceFiles = new ArrayList<File>();
		getFiles(temp.getRoot(), sourceFiles);
		compile(sourceFiles);
	}

	protected String getTemporaryFolder() {
		return temp.getRoot().getAbsolutePath();
	}

	// - - --  ---------  - - --   -------- - -   - ----- -- ----------  -- ---

	private void recursiveDelete(File directory) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				recursiveDelete(file);
			} else {
				file.delete();
			}
		}
	}

	private void getFiles(File file, List<File> files) {
		if (file.isFile() && file.getName().endsWith(".java")) {
			files.add(file);
		} else {
			for (File next : file.listFiles()) {
				getFiles(next, files);
			}
		}
	}

	private void compile(List<File> sourceFiles) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager
			= compiler.getStandardFileManager(diagnostics, Locale.getDefault(), Charset.defaultCharset());

		List<String> options = new ArrayList<String>();
		options.add("-classpath");
		options.add(System.getProperty("java.class.path"));

		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFiles);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		task.call();

		try {
			fileManager.close();
		} catch (IOException e) {
			// nothing
		}

		StringBuilder errors = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> error : diagnostics.getDiagnostics()) {
			if (error.getKind() != Diagnostic.Kind.NOTE) {
				errors.append("\n\n").append(error.getSource().getName())
					  .append(" (").append(error.getLineNumber()).append(",").append(error.getColumnNumber()).append(")\n")
					  .append(error.getMessage(Locale.getDefault()))
				;
			}
		}

		if (errors.length() != 0) {
			throw new RuntimeException("The compilation was completed with errors."+errors.toString()+"\n");
		}
	}
}
