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

import unquietcode.tools.flapi.builder.DescriptorGenerator;
import unquietcode.tools.flapi.helpers.DescriptorHelperImpl;

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * The main descriptor for Flapi, used to generate other descriptors.
 */
public class MainDescriptor {

	public static void main(String[] args) {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.builder")
				.setStartingMethodName("create")
				.setDescriptorName("Descriptor")

				.addMethod("setPackage(String packageName)").exactly(1)
				.addMethod("setDescriptorName(String descriptorName)").exactly(1)
				.addMethod("setStartingMethodName(String methodName)").atMost(1)
				.addMethod("setReturnType(Class returnType)").atMost(1)
				.addMethod("enableCondensedClassNames()").atMost(1)
				.addMethod("build()").last(Descriptor.class)

				.startBlock("Method", "addMethod(String methodSignature)").any()
					.addMethod("exactly(int num)").last()
					.addMethod("any()").last()
					.addMethod("last()").last()
					.addMethod("last(Class returnType)").last()
					.addMethod("atLeast(int num)").last()
					.addMethod("atMost(int num)").last()
					.addMethod("between(int atLeast, int atMost)").last()

					.startBlock("BlockChain", "addBlockChain()").atMost(1)
						.addMethod("addBlockReference(String blockName)").last()
						.addBlockReference("Block", "startBlock(String blockName)").last()
						.addBlockReference("BlockChain", "addBlockChain()").atMost(1)
					.endBlock()
				.endBlock()

				.startBlock("Block", "startBlock(String blockName, String methodSignature)")
					.addBlockChain()
						.addBlockReference("Method")
					.any()

					.addMethod("addBlockReference(String blockName, String methodSignature)")
						.addBlockChain().addBlockReference("Method")
					.any()

					.addBlockReference("Method", "addMethod(String methodSignature)").any()

					.addBlockReference("Block", "startBlock(String blockName, String methodSignature)")
						.addBlockChain().addBlockReference("Method")
					.any()

					.addMethod("endBlock()").last()
				.endBlock()

			.build()
		;

		builder.writeToFolder(args[0]);
	}
}