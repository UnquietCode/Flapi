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
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.generator.DescriptorGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.outline.DescriptorOutline;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class Descriptor {
	final DescriptorOutline _descriptor;
	
	public Descriptor(DescriptorHelper helper) {
		if (!(helper instanceof DescriptorHelperImpl)) {
			throw new RuntimeException("Wrong helper instance! (this is an internal error)");
		}
		
		_descriptor = ((DescriptorHelperImpl) helper).outline;
		checkDescriptor();
	}

	// TODO methods for file writing, stream writing

	public void writeCodeModel() {
		DescriptorGenerator generator = new DescriptorGenerator(_descriptor);
		JCodeModel model = generator.generate();
		
		CodeWriter.writeToConsole(model);
		CodeWriter.writeToDirectory(model, "/Users/bfagin/Desktop/lmbuilder");
	}
	
	private void checkDescriptor() {
		// TODO
	}
}
