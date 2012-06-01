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
import unquietcode.tools.flapi.generator.DescriptorGenerator;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class Descriptor {
	final DescriptorOutline _descriptor;
	private JCodeModel model;


	public Descriptor(DescriptorHelperImpl helper) {
		_descriptor = helper.outline;
	}

	public void writeToStream(OutputStream stream) {
		CodeWriter.writeToStream(generate(), stream);
	}

	public void writeToFolder(String folder) {
		File f = new File(folder);
		if (!f.exists()) {
			throw new DescriptorBuilderException("Folder '"+folder+"' does not exist.");
		}

		if (!f.isDirectory()) {
			throw new DescriptorBuilderException("Folder '"+folder+"' is not a folder!");
		}

		if (!f.canWrite()) {
			throw new DescriptorBuilderException("Cannot write to folder '"+folder+"'.");
		}

		CodeWriter.writeToDirectory(generate(), f);
		CodeWriter.writeRequiredClasses(f);
	}

	private JCodeModel generate() {
		if (model != null) {
			return model;
		}

		DescriptorGenerator generator = new DescriptorGenerator(_descriptor);
		model = generator.generate();
		return model;
	}
}
