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

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * The main descriptor for Flapi, used to generate other descriptors.
 */
public class MainDescriptor {

	public static void main(String[] args) {
		Descriptor builder = Flapi.builder()

			// descriptor configuration
			.setPackage("unquietcode.tools.flapi.builder")
			.setStartingMethodName("create")
			.setDescriptorName("Descriptor")
			.enableCondensedClassNames()

			// descriptor methods
			.addMethod("setPackage(String packageName)")
				.withDocumentation()
					.addContent("set the root package name to use for the generated classes")
				.finish()
			.exactly(1)

			.addMethod("setDescriptorName(String descriptorName)")
				.withDocumentation()
					.addContent("set the name of the top level descriptor")
				.finish()
			.exactly(1)

			.addMethod("setStartingMethodName(String methodName)")
				.withDocumentation()
					.addContent("set the name of the generator's starting method (default is 'create')")
				.finish()
			.atMost(1)

			.addMethod("setReturnType(Class returnType)")
				.withDocumentation()
					.addContent("set the return type for the top level descriptor (default is void)")
				.finish()
			.atMost(1)

			.addMethod("enableCondensedClassNames()")
				.withDocumentation()
					.addContent("Allow class names to be condensed, at the cost of no longer being\n")
					.addContent("humanly readable. If your generated class names are too long to be\n")
					.addContent("compiled, you will have to use this.")
				.finish()
			.atMost(1)

			.addMethod("build()")
				.withDocumentation()
					.addContent("Finish work and build the descriptor. This should only be called once.")
				.finish()
			.last(Descriptor.class)

			// Method
			.startBlock("Method", "addMethod(String methodSignature)")
				.withDocumentation()
					.addContent("Add a new method to the top level descriptor block.")
				.finish()
			.any()

				// @Deprecated marker
				.addMethod("markAsDeprecated(String reason)")
					.withDocumentation()
						.addContent("Marks this method with a Deprecated annotation.\n")
						.addContent("Also adds a note to the Javadocs.")
					.finish()
				.atMost(1)

				// Documentation
				.startBlock("Documentation", "withDocumentation()")
					.withDocumentation()
						.addContent("Add javadoc style documentation to the method.")
					.finish()

					.addAlias("withDocumentation(String documentation)")
				.atMost(1)
					.addMethod("addContent(String content)")
						.withDocumentation()
							.addContent("add more content to the Javadocs")
						.finish()
					.any()
					.addMethod("finish()")
						.withDocumentation()
							.addContent("finish writing the documentation")
						.finish()
					.last()
				.endBlock()

				// method quantities
				.addMethod("exactly(int num)").last()
				.addMethod("any()").last()
				.addMethod("last()").last()
				.addMethod("last(Class returnType)").last()
				.addMethod("atLeast(int num)").last()
				.addMethod("atMost(int num)").last()
				.addMethod("between(int atLeast, int atMost)").last()

				// BlockChain
				.startBlock("BlockChain", "addBlockChain()").atMost(1)
					.addMethod("addBlockReference(String blockName)").last()
					.addBlockReference("Block", "startBlock(String blockName)").last()
					.addBlockReference("Block", "startBlock()").last()
					.addBlockReference("BlockChain", "addBlockChain()").atMost(1)
				.endBlock()

				// Method Aliases
				.addMethod("addAlias(String methodSignature)").any()
			.endBlock()

			// Block
			.startBlock("Block", "startBlock(String blockName, String methodSignature)")
				.addBlockChain()
					.addBlockReference("Method")
				.any()

				.addMethod("addBlockReference(String blockName, String methodSignature)")
					.addBlockChain().addBlockReference("Method")
				.any()

				.addBlockReference("Method", "addMethod(String methodSignature)")
				.any()

				.addBlockReference("Block", "startBlock(String blockName, String methodSignature)")
					.addBlockChain().addBlockReference("Method")
				.any()

				.addBlockReference("Block", "startBlock(String methodSignature)")
					.addBlockChain().addBlockReference("Method")
				.any()

				.addMethod("endBlock()").last()
			.endBlock()

			// Block Reference (copied out of Block's use)
			.addMethod("addBlockReference(String blockName, String methodSignature)")
				.addBlockChain().addBlockReference("Method")
			.any()

			.addBlockReference("Block", "startBlock(String methodSignature)")
				.addBlockChain().addBlockReference("Method")
			.any()
		.build();

		//builder.writeToFolder(args[0]);
		builder.writeToFolder("/Users/bfagin/Downloads/tmp");
	}
}