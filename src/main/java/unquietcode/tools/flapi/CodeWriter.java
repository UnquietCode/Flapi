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

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

import java.io.*;
import java.util.Scanner;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class CodeWriter {

	public static void writeToStream(JCodeModel model, OutputStream stream) {
		SingleStreamCodeWriter writer = new SingleStreamCodeWriter(stream);

		try {
			model.build(writer);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		System.out.println(stream.toString());
	}

	public static void writeToDirectory(JCodeModel model, File directory) {
		try {
			FileCodeWriter fileWriter = new FileCodeWriter(directory, false);
			model.build(fileWriter);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void writeRequiredClasses(File folder) {
		String path = Constants.getSupportPath(folder);
		File dir = new File(path);

		if (!dir.exists()) {
			boolean create = dir.mkdirs();
			if (!create) {
				throw new DescriptorBuilderException("Unable create output directory '"+path+"'.");
			}
		}

		for (String requiredFile : Constants.REQUIRED_FILES) {
			String resourceName = requiredFile+".avaj";
			String fileName = requiredFile+".java";   // get it? :)
			copyFile(getResourceFile(resourceName), createFile(dir.getAbsolutePath(), fileName));
		}
	}

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	private static InputStream getResourceFile(String name) {
		InputStream stream = CodeWriter.class.getClassLoader().getResourceAsStream(name);

		if (stream == null) {
			throw new DescriptorBuilderException("Cannot find file '"+name+"' (this is an internal error).");
		}

		return stream;
	}

	private static File createFile(String path, String name) {
		File file = new File(path+File.separator+name);

		if (file.exists()) {
			if (!file.delete()) {
				throw new DescriptorBuilderException("Could not remove old file '"+file.getAbsolutePath()+"'.");
			}
		}

		boolean create;
		try {
			create = file.createNewFile();
		} catch (IOException ex) {
			throw new DescriptorBuilderException("Error creating file.", ex);
		}

		if (!create) {
			throw new DescriptorBuilderException("Error creating file.");
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
			throw new DescriptorBuilderException("Error while writing files.", ex);
		} finally {
			if (destination != null) {
				try {destination.close();} catch (Exception ex) { /* nothing */ }
			}
		}
	}
}
