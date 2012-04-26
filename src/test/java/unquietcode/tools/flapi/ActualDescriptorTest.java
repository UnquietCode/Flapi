package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.builder.DescriptorGenerator;

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * Test which should produce the actual descriptor used by the tool.
 * Very exciting!
 */
public class ActualDescriptorTest {

	@Test
	public void descriptorGenerator() {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.builder")
				.setStartingMethodName("create")
				.setDescriptorName("Descriptor")
				.setReturnType(Descriptor.class)

				.addMethod("showLog(boolean value)").once()
				.addMethod("setPackage(String packageName)").between(1,1)
				.addMethod("setDescriptorName(String descriptorName)").between(1,1)
				.addMethod("setStartingMethodName(String methodName)").once() //default to "create" if not called
				.addMethod("setReturnType(Class returnType)").between(1,1)
				.addMethod("build()").last()

				.startBlock("Method", "addMethod(String methodSignature)").any()
					.addMethod("once()").last()
					.addMethod("any()").last()
					.addMethod("last()").last()
					.addMethod("atLeast(int num)").last()
					.addMethod("atMost(int num)").last()
					.addMethod("between(int atLeast, int atMost)").last()
					.addBlockReference("BlockChain", "addBlockChain()").once()
				.endBlock()

				.startBlock("Block", "startBlock(String blockName, String methodSignature)").any()
					.addBlockChain()
						.addBlockReference("Method")

					.addMethod("addBlockReference(String blockName, String methodSignature)")
						.addBlockChain().addBlockReference("Method")
					.any()

					.addBlockReference("Method", "addMethod(String methodSignature)").any()
					.addBlockReference("Block", "startBlock(String blockName, String methodSignature)").any()
					.addMethod("endBlock()").last()

					.startBlock("BlockChain", "addBlockChain()").once()
						.addMethod("addBlockReference(String blockName)").last()
						.addBlockReference("Block", "startBlock(String blockName, String methodSignature)").last()
						.addBlockReference("BlockChain", "addBlockChain()").once()
					.endBlock()
				.endBlock()

			.build()
		;

		builder.writeCodeModel();
	}
}