/*******************************************************************************
 Copyright 2013 Benjamin Fagin

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Ben Fagin
 * @version 2013-07-01
 */
public final class ExtractRuntime {

	/**
	 * Extracts the various runtime classes to the directory
	 * specified as the first argument.
	 *
	 * This allows the generated library to be more portable,
	 * and mimics the behavior in versions <= 0.3
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new RuntimeException("Usage: <outputPath>");
		}

		File out = new File(args[0]);
		writeRequiredClasses(out);
	}

	public static void writeRequiredClasses(File folder) {
		String path = getSupportPath(folder);
		File dir = new File(path);

		if (!dir.exists()) {
			boolean create = dir.mkdirs();
			if (!create) {
				throw new RuntimeException("Unable create output directory '"+path+"'.");
			}
		}

		try {
			URL _sources = ExtractRuntime.class.getClassLoader().getResources("sources").nextElement();
			File sources = new File(_sources.getFile());
			File[] files = sources.listFiles();

			for (File file : files) {
				InputStream stream = getResourceFile("sources/" + file.getName());
				File destination = createFile(dir.getAbsolutePath(), file.getName());
				copyFile(stream, destination);
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String getSupportPath(File folder) {
		String path = folder.getAbsolutePath();
		path += File.separator;
		path += "unquietcode"+File.separator+"tools"+File.separator+"flapi"+File.separator+"support"+File.separator;

		return path;
	}

	private static InputStream getResourceFile(String name) {
		InputStream stream = ExtractRuntime.class.getClassLoader().getResourceAsStream(name);

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

	private static void copyFile(InputStream sourceFile, File destFile) {
		FileOutputStream destination = null;

		try {
			String data = new Scanner(sourceFile).useDelimiter("\\A").next();
			destination = new FileOutputStream(destFile);
			destination.write(data.getBytes());
		} catch (Exception ex) {
			throw new RuntimeException("Error while writing files.", ex);
		} finally {
			if (destination != null) {
				try {destination.close();} catch (Exception ex) { /* nothing */ }
			}
		}
	}
}
