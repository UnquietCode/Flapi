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

package unquietcode.tools.flapi.plugin;


import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.ExtractRuntime;
import unquietcode.tools.flapi.plugin.compile.CharSequenceJavaFileObject;
import unquietcode.tools.flapi.plugin.compile.ClassFileManager;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Given a static method which returns a {@link unquietcode.tools.flapi.Descriptor} object,
 * generate the classes and write them out to the specified output
 * directory.
 *
 * @author Ben Fagin
 */
public abstract class PluginHelper {
	private final String classesDirectory;
	private final String sourcesDirectory;
	private boolean writeClasses = true;
	private boolean writeSources = true;
	private boolean includeRuntime = true;

	public PluginHelper(String classesDirectory, String sourcesDirectory) {
		this.classesDirectory = Objects.requireNonNull(classesDirectory, "classes directory is required");
		this.sourcesDirectory = Objects.requireNonNull(sourcesDirectory, "sources directory is required");
	}

	public void setWriteClasses(boolean writeClasses) {
		this.writeClasses = writeClasses;
	}

	public void setWriteSources(boolean writeSources) {
		this.writeSources = writeSources;
	}

	public void setIncludeRuntime(boolean includeRuntime) {
		this.includeRuntime = includeRuntime;
	}

	protected abstract Exception handleError(String message, Throwable cause) throws Exception;
	protected abstract Exception handleFailure(String message, Throwable cause) throws Exception;

	protected abstract void logInfo(String message);
	protected abstract void logWarn(String message);
	protected abstract void logError(String message);

	protected Exception handleError(String message) throws Exception {
		return handleError(message, null);
	}

	protected Exception handleFailure(String message) throws Exception {
		return handleFailure(message, null);
	}

	protected abstract URLClassLoader getCompiledClassloader() throws Exception;


	public void processDescriptors(String[] descriptorClasses) throws Exception {

		// have we seen at least one descriptor?
		boolean atLeastOne = false;

		for (String descriptorClass : descriptorClasses) {
			descriptorClass = descriptorClass.trim();

			if (descriptorClass.isEmpty()) {
				continue;
			}

			if (descriptorClass.trim().equals("change.me")) {
				continue;
			}

			logInfo("processing descriptor " + descriptorClass);
			processDescriptor(descriptorClass);
			atLeastOne = true;
		}

		if (!atLeastOne) {
			logWarn("No descriptor classes were specified.");
		}
	}

	public void processDescriptor(String descriptorClass) throws Exception {
		Method method;
		DescriptorMaker descriptorMaker;

		// instantiate the class
		URLClassLoader classLoader;
		Class<?> _descriptorClass;

		try {
			classLoader = getCompiledClassloader();
			_descriptorClass = classLoader.loadClass(descriptorClass);
		} catch (Exception ex) {
			throw handleError("could not load class", ex);
		}

		// ensure that it implements the interface
		if (!DescriptorMaker.class.isAssignableFrom(_descriptorClass)) {
			throw handleError("object must implement the DescriptorMaker interface");
		}

		// lookup the method
		try {
			method = _descriptorClass.getMethod("descriptor");
		} catch (NoSuchMethodException ex) {
			throw handleError("method cannot be found", ex);
		}

		// instantiate the object
		try {
			descriptorMaker = (DescriptorMaker) _descriptorClass.newInstance();
		} catch (Exception ex) {
			throw handleError("could not instantiate DescriptorMaker object", ex);
		}

		// execute and get the descriptor
		Descriptor descriptor;
		try {
			descriptor = (Descriptor) method.invoke(descriptorMaker);
		} catch (IllegalAccessException ex) {
			throw handleError("method not accessible", ex);
		} catch (InvocationTargetException ex) {
			throw handleError("error while executing method", ex.getTargetException());
		}

		// ensure not null
		if (descriptor == null) {
			throw handleError("method returned null");
		}

		// compile and write out the classes
		if (writeClasses) {
			new File(classesDirectory).mkdirs();
			compileAndWriteClasses(descriptor, classLoader);

			if (includeRuntime) {
				ExtractRuntime.writeRequiredClasses(classesDirectory);
			}
		}

		// write out the source files
		if (writeSources) {
			new File(sourcesDirectory).mkdirs();
			descriptor.writeToFolder(sourcesDirectory);

			if (includeRuntime) {
				ExtractRuntime.writeRequiredSources(sourcesDirectory);
			}
		}
	}

	private List<JavaFileObject> getSourceFiles(Descriptor descriptor) {
		Map<String, OutputStream> streams = descriptor.writeToStreams(new Iterator<OutputStream>() {
			public OutputStream next() { return new ByteArrayOutputStream(); }
			public boolean hasNext() { return true; }
			public void remove() { throw new UnsupportedOperationException("nope"); }
		});

		final List<JavaFileObject> files = new ArrayList<JavaFileObject>();

		for (Map.Entry<String, OutputStream> entry : streams.entrySet()) {
			String name = entry.getKey();
			name = name.substring(0, name.length()-5); // removes .java

			ByteArrayOutputStream stream = (ByteArrayOutputStream) entry.getValue();
			JavaFileObject file = new CharSequenceJavaFileObject(name, stream.toString());
			files.add(file);
		}

		return files;
	}

	private ClassLoader compileAndWriteClasses(Descriptor descriptor, URLClassLoader classLoader) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		JavaFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(null, null, null), classesDirectory);

		List<String> options = new ArrayList<String>();
		options.add("-classpath");
		options.add(makeClasspath(classLoader));
		options.add("-source");
		options.add("1.6");
		options.add("-target");
		options.add("1.6");

		Iterable<? extends JavaFileObject> compilationUnits = getSourceFiles(descriptor);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		task.call();

		try {
			fileManager.close();
		} catch (IOException e) {
			// nothing
		}

		boolean atLeastOneError = false;

		for (Diagnostic<? extends JavaFileObject> error : diagnostics.getDiagnostics()) {

			if (error.getKind() != Diagnostic.Kind.NOTE) {
				StringBuilder message = new StringBuilder()
					.append(error.getSource().getName())
					.append(" (").append(error.getLineNumber()).append(",").append(error.getColumnNumber()).append(")\n")
					.append(error.getMessage(Locale.getDefault()));

				logError(message.toString());
				atLeastOneError = true;
			}
		}

		if (atLeastOneError) {
			throw handleError("The compilation was completed with errors.");
		}

		return fileManager.getClassLoader(StandardLocation.CLASS_PATH);
	}

	private static String makeClasspath(URLClassLoader classLoader) {
		StringBuilder buffer = new StringBuilder("\"");

		for (URL url : classLoader.getURLs()) {
			final File file;
			try {
				file = new File(url.toURI());
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}

			buffer.append(file);
			buffer.append(System.getProperty("path.separator"));
		}

		return buffer.append("\"").toString();
	}
}