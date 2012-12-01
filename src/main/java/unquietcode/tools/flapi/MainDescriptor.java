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
			.addMethod("setPackage(String packageName)").exactly(1)
			.addMethod("setDescriptorName(String descriptorName)").exactly(1)
			.addMethod("setStartingMethodName(String methodName)").atMost(1)
			.addMethod("setReturnType(Class returnType)").atMost(1)
			.addMethod("enableCondensedClassNames()").atMost(1)
			//.addMethod("withAnnotations(Annotation...annotations)").any()
			.addMethod("build()").last(Descriptor.class)

			// Method
			.startBlock("Method", "addMethod(String methodSignature)").any()

				// Documentation
//				.startBlock("Documentation", "withDocumentation()").atMost(1)
//					.addMethod("withContent(String content)").any()
//					.addMethod("withParameter(String parameterName, String parameterValue)").any()
//				.endBlock()

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
					.addBlockReference("BlockChain", "addBlockChain()").atMost(1)
				.endBlock()
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

		builder.writeToFolder(args[0]);
	}
}