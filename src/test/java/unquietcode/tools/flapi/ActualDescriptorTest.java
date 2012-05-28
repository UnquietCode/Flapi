package unquietcode.tools.flapi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import unquietcode.tools.flapi.builder.*;

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * Test which should produce the actual descriptor used by the tool.
 * Very exciting!
 */
public class ActualDescriptorTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@Test
	public void descriptorGenerator() {
		main(new String[]{temp.getRoot().getAbsolutePath()});
	}

	public static void main(String[] args) {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.builder")
				.setStartingMethodName("create")
				.setDescriptorName("Descriptor")
				.setReturnType(Descriptor.class)
				.enableCondensedClassNames(false)

				.addMethod("setPackage(String packageName)").exactly(1)
				.addMethod("setDescriptorName(String descriptorName)").exactly(1)
				.addMethod("setStartingMethodName(String methodName)").atMost(1)
				.addMethod("setReturnType(Class returnType)").exactly(1)
				.addMethod("enableCondensedClassNames(boolean value)").atMost(1)
				.addMethod("build()").last()

				.startBlock("Method", "addMethod(String methodSignature)").any()
					.addMethod("exactly(int num)").last()
					.addMethod("any()").last()
					.addMethod("last()").last()
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