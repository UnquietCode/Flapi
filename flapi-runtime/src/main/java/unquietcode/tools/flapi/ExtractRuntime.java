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

package unquietcode.tools.flapi;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Ben Fagin
 * @version 2013-07-01
 */
public final class ExtractRuntime {
	private static final String SOURCE_PATH = "sources";


	/**
	 * Extracts the various runtime classes to the directory
	 * specified as the first argument.
	 *
	 * This allows the generated library to be more portable,
	 * and mimics the behavior in versions &lt;= 0.3
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new RuntimeException("Usage: <outputPath>");
		}

		writeRequiredSources(args[0]);
	}

	public static void writeRequiredSources(String folder) {
		writeRequiredSources(new File(folder));
	}

	public static void writeRequiredSources(File folder) {
		writeFiles(folder, true);
	}

	public static void writeRequiredClasses(String folder) {
		writeRequiredClasses(new File(folder));
	}

	public static void writeRequiredClasses(File folder) {
		writeFiles(folder, false);
	}

	private static void writeFiles(File folder, boolean useSources) {
		Class[] roots = {
			unquietcode.tools.flapi.runtime.PackageMarker.class
		};

		for (Class root : roots) {
			writeFiles(folder, root, useSources);
		}
	}

	private static void writeFiles(File folder, Class packageMarker, boolean useSources) {
		String writePath = getResourcePath(packageMarker, folder);
		File outputDirectory = new File(writePath);

		if (!outputDirectory.exists()) {
			boolean create = outputDirectory.mkdirs();
			if (!create) {
				throw new RuntimeException("Unable create output directory '"+writePath+"'.");
			}
		}

		final String extension = useSources ? ".java" : ".class";
		final String prefix = useSources
							? SOURCE_PATH+"/"+packagePath(packageMarker, "/")
							: packagePath(packageMarker, "/");
		try {
			URL _sources = Thread.currentThread().getContextClassLoader().getResources(prefix).nextElement();
			String sources = _sources.getFile();

			if (sources.startsWith("file:") && sources.contains("!")) {
				String jarPath = sources.split("!")[0];
				handleJarFile(jarPath, prefix, extension, outputDirectory);
			} else {
				handleFileSystem(sources, extension, outputDirectory);
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static void writeFile(File outputDirectory, String path, InputStream stream) {
		File destination = createFile(outputDirectory.getAbsolutePath(), path);
		copyFile(stream, destination);
	}

	private static void handleFileSystem(String folderPath, String extension, File outputDirectory)
		throws IOException
	{
		File[] files = new File(folderPath).listFiles();

		if (files == null) {
			throw new RuntimeException("could not read from path "+folderPath);
		}

		for (File file : files) {
			final String name = file.getName();

			if (!name.endsWith(extension)) {
				continue;
			}

			writeFile(outputDirectory, name, new FileInputStream(file));
		}
	}

	private static void handleJarFile(String jarPath, String prefix, String extension, File outputDirectory)
		throws IOException
	{
		URL jar = new URL(jarPath);
		ZipInputStream zip = new ZipInputStream(jar.openStream());
		ZipEntry entry;

		while ((entry = zip.getNextEntry()) != null) {
			final String name = entry.getName();

			if (!name.endsWith(extension)) {
				continue;
			}

			if (!name.startsWith(prefix)) {
				continue;
			}

			String resourceName = name.substring(prefix.length());
			writeFile(outputDirectory, resourceName, getResourceFile(name));
		}
	}

	public static String getResourcePath(Class marker, File folder) {
		String path = folder.getAbsolutePath();
		path += File.separator;
		path += packagePath(marker, File.separator)+File.separator;

		return path;
	}

	private static String packagePath(Class marker, String separator) {
		StringBuilder sb = new StringBuilder();

		for (String segment : marker.getPackage().getName().split("\\.")) {
			sb.append(segment).append(separator);
		}

		return sb.toString().substring(0, sb.length()-1);
	}

	private static InputStream getResourceFile(String name) {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);

		if (stream == null) {
			throw new RuntimeException("Cannot find file '"+name+"' (this is an internal error).");
		}

		return stream;
	}

	private static File createFile(String path, String name) {
		File file = new File(path+File.separator+name);

		if (file.exists()) {
			if (!file.delete()) {
				throw new RuntimeException("Could not remove old file '"+file.getAbsolutePath()+"'.");
			}
		}

		boolean create;
		try {
			create = file.createNewFile();
		} catch (IOException ex) {
			throw new RuntimeException("Error creating file.", ex);
		}

		if (!create) {
			throw new RuntimeException("Error creating file.");
		}

		return file;
	}

	private static void copyFile(InputStream source, File destFile) {
		FileOutputStream destination = null;

		try {
			destination = new FileOutputStream(destFile);
			int read;
			byte[] buffer = new byte[512];

			while ((read = source.read(buffer)) > 0) {
				destination.write(buffer, 0, read);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Error while writing files.", ex);
		} finally {
			if (destination != null) {
				try {destination.close();} catch (Exception ex) { /* nothing */ }
			}
		}
	}
}
