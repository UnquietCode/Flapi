package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * The main descriptor for Flapi, used to generate other descriptors.
 */
public class MainDescriptor implements DescriptorMaker {
	private static final int DOC_GROUP = 1;
	private static final int RETURN_TYPE_GROUP = 2;


	@Override
	public Descriptor descriptor() {
		Descriptor builder = Flapi.builder()

			// descriptor configuration
			.setPackage("unquietcode.tools.flapi.builder")
			.setStartingMethodName("create")
			.setDescriptorName("Descriptor")
			.enableCondensedClassNames()

			// descriptor methods
			.addMethod("setPackage(String packageName)")
				.withDocumentation("set the root package name to use for the generated classes")
			.exactly(1)

			.addMethod("setDescriptorName(String descriptorName)")
				.withDocumentation("set the name of the top level descriptor")
			.exactly(1)

			.addMethod("setStartingMethodName(String methodName)")
				.withDocumentation("set the name of the generator's starting method (default is 'create')")
			.atMost(1)

			.addMethod("setReturnType(Class returnType)")
				.withDocumentation("set the return type for the top level descriptor (default is void)")
			.atMost(1, RETURN_TYPE_GROUP)

			.addMethod("setReturnType(String returnType)")
				.withDocumentation("set the return type for the top level descriptor (default is void)")
			.atMost(1, RETURN_TYPE_GROUP)

			.addMethod("enableCondensedClassNames()")
				.withDocumentation()
					.addContent("Allow class names to be condensed, at the cost of no longer being\n")
					.addContent("humanly readable. If your generated class names are too long to be\n")
					.addContent("compiled, you will have to use this.")
				.finish()
			.atMost(1)

			.addMethod("build()")
				.withDocumentation("Finish work and build the descriptor. This should only be called once.")
			.last(Descriptor.class)

			// Method
			.startBlock("Method", "addMethod(String methodSignature)")
				.withDocumentation("Add a new method to the top level descriptor block.")
			.any()

				// @Deprecated marker
				.addMethod("markAsDeprecated(String reason)")
					.withDocumentation()
						.addContent("Marks this method with a Deprecated annotation.\n")
						.addContent("Also adds a note to the Javadocs.")
					.finish()
				.atMost(1)

   				// user Annotations
				.startBlock("Annotations", "addAnnotation(Class annotation)")
					.withDocumentation()
						.addContent("Adds a custom annotation.\n")
					.finish()
                .any()
                    .startBlock("AnnotationParam", "withParam(String param)").any()
                        .addMethod("havingValue(String value)").last()
                        .addMethod("havingValue(Enum value)").last()
                        .addMethod("havingValue(boolean value)").last()
                        .addMethod("havingValue(int value)").last()
                        .addMethod("havingValue(long value)").last()
                        .addMethod("havingValue(float value)").last()
                        .addMethod("havingValue(double value)").last()
                        .addMethod("havingValue(short value)").last()
                        .addMethod("havingValue(byte value)").last()
                    .endBlock()
                    .startBlock("AnnotationClassParam", "withClassParam(String param)").any()
                        .addMethod("havingValue(Class value)").last()
                        .addMethod("havingValue(String value)").last()
                    .endBlock()
                    .addMethod("finish()").last()
                .endBlock()
                .addBlockReference("Annotations", "addAnnotation(String annotation)")
                .any()

				// Documentation
				.startBlock("Documentation", "withDocumentation()")
					.withDocumentation("Add javadoc style documentation to the method.")
				.atMost(1, DOC_GROUP)
					.addMethod("addContent(String content)")
						.withDocumentation("add more content to the Javadocs")
					.any()
					.addMethod("finish()")
						.withDocumentation("finish writing the documentation")
					.last()
				.endBlock()

				.addMethod("withDocumentation(String documentation)")
					.withDocumentation("Add javadoc style documentation to the method.")
				.atMost(1, DOC_GROUP)

				// method quantities
				.addMethod("exactly(int num)")
					.withDocumentation("expect the method [X, X] times")
				.last()

				.addMethod("any()")
					.withDocumentation("expect the method [0, inf) times")
				.last()

				.addMethod("any(int group)")
					.withDocumentation("expect the method [0, inf) times, and assign a group number")
				.last()

				.addMethod("last()")
					.withDocumentation("mark the method as terminal, exiting the block when called")
				.last()

				.addMethod("last(Class returnType)")
					.withDocumentation()
						.addContent("mark the method as terminal, returning an object of the given ")
						.addContent("type when called")
					.finish()
				.last()

				.addMethod("last(String returnType)")
					.withDocumentation()
						.addContent("Mark the method as terminal, returning an object of the given ")
						.addContent("type when called. The type is specified as a FQCN.")
					.finish()
				.last()

				.addMethod("atLeast(int num)")
					.withDocumentation("expect the method [X, inf) times")
				.last()

				.addMethod("atMost(int num)")
					.withDocumentation("expect the method [0, X] times")
				.last()

				.addMethod("atMost(int num, int group)")
					.withDocumentation("expect the method [0, X] times, and assign a group number")
				.last()

				.addMethod("between(int atLeast, int atMost)")
					.withDocumentation("expect the method [atLeast, atMost] times")
				.last()

				.addMethod("after(int group)")
					.withDocumentation("expose the method only after the specified group is finished")
				.atMost(1)

				// BlockChain
				.startBlock("BlockChain", "addBlockChain()")
					.withDocumentation()
						.addContent("Add a BlockChain, which is a sequence of blocks  which must be")
						.addContent("passed through\n before the method returns.")
					.finish()
				.atMost(1)
					.addMethod("addBlockReference(String blockName)")
						.withDocumentation("add a reference to an existing block")
					.any()

					.addBlockReference("Block", "startBlock(String blockName)")
						.withDocumentation("create a new block")
					.any()

					.addBlockReference("Block", "startBlock()")
						.withDocumentation("create a new anonymous block (which cannot be referenced from anywhere)")
					.any()

					.addMethod("end()")
						.withDocumentation("finish creating the block chain for this method")
					.last()
				.endBlock()
			.endBlock()

			// Block
			.startBlock("Block", "startBlock(String blockName, String methodSignature)")
				.withDocumentation("Starts a new block.")
				.addBlockChain()
					.addBlockReference("Method")
				.end()
			.any()

				.addMethod("addBlockReference(String blockName, String methodSignature)")
					.withDocumentation("add a new method which proceeds to an existing block")
					.addBlockChain()
						.addBlockReference("Method")
					.end()
				.any()

				.addBlockReference("Method", "addMethod(String methodSignature)")
					.withDocumentation("add a new method to the block")
				.any()

				.addBlockReference("Block", "startBlock(String blockName, String methodSignature)")
					.withDocumentation()
						.addContent("Start a new block, nested inside the current one.\n")
						.addContent("The block can be referenced from outside by using the designated name.")
					.finish()

					.addBlockChain()
						.addBlockReference("Method")
					.end()
				.any()

				.addBlockReference("Block", "startBlock(String methodSignature)")
					.withDocumentation()
						.addContent("Start a new anonymous block, nested inside the current one.\n")
						.addContent("The block cannot be referenced from outside, as it has no name.")
					.finish()

					.addBlockChain()
						.addBlockReference("Method")
					.end()
				.any()

				.addMethod("addEnumSelector(Class clazz, String methodSignature)")
					.withDocumentation("Adds an enum selector, by passing in an enum class.")
					.addBlockChain()
						.addBlockReference("Method")
					.end()
				.any()

				.addMethod("endBlock()")
					.withDocumentation("finish editing of the current block")
				.last()
			.endBlock()

			// Block Reference for the top level block
			.addMethod("addBlockReference(String blockName, String methodSignature)")
				.withDocumentation("add a new method which proceeds to an existing block")
				.addBlockChain()
					.addBlockReference("Method")
				.end()
			.any()

			// anonymous blocks for the top level block
			.addBlockReference("Block", "startBlock(String methodSignature)")
				.withDocumentation("Starts a new block.")
				.addBlockChain()
					.addBlockReference("Method")
				.end()
			.any()

			// enum selector for the top level block
			.addMethod("addEnumSelector(Class clazz, String methodSignature)")
				.withDocumentation("Adds an enum selector, by passing in an enum class.")
				.addBlockChain()
					.addBlockReference("Method")
				.end()
			.any()
		.build();

		return builder;
	}
}