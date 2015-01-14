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

package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.builder.Descriptor.DescriptorBuilder_2m1_4f_2m2_4f_2m3_4f_2m5_4f_2m6_4f_2m7_4f_2m8_4f_2m9_4f_2m10_4f_2m11_4f;

/**
 * @author Ben Fagin
 * @version 2015-01-13
 */
public abstract class BaseDescriptor implements DescriptorMaker {

	protected DescriptorBuilder_2m1_4f_2m2_4f_2m3_4f_2m5_4f_2m6_4f_2m7_4f_2m8_4f_2m9_4f_2m10_4f_2m11_4f<Void> baseBuilder() {
		return Flapi.builder()

			.enableCondensedClassNames()


			// descriptor methods

			.addMethod("setPackage(String packageName)")
				.withDocumentation("set the root package name to use for the generated classes")
			.atMost(1)

			.addMethod("setStartingMethodName(String methodName)")
				.withDocumentation("set the name of the generator's starting method (default is 'create')")
			.atMost(1)

			.addMethod("enableCondensedClassNames()")
				.withDocumentation()
					.addContent("Allow class names to be condensed, at the cost of no longer being\n")
					.addContent("humanly readable. If your generated class names are too long to be\n")
					.addContent("compiled, you will have to use this.")
				.finish()
			.atMost(1)

			.addMethod("disableTimestamps()")
				.withDocumentation()
					.addContent("Disable the use of timestamps in the generated source code.\n")
					.addContent("This will eliminate changes between successive executions so long\n")
					.addContent("as the same version of the tool is used each time.")
				.finish()
			.atMost(1)

			.addMethod("build()").last(Descriptor.class)
		;
	}
}