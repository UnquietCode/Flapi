package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class BlockReference_T extends AbstractCompiledTest {

	@Test
	public void testThatBlockReferencesKeepBlockChain() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("some.thing")

			.startBlock("One", "one()").any()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Two", "two()")
				.addBlockChain()
					.addBlockReference("One")
					.addBlockReference("Three")
				.end()
			.any()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Three", "three()").any()
				.addBlockReference("Two", "twoRef()").last() // this should go One->Two->exit
			.endBlock()

			.addMethod("exit()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		addTestClassMethod("Test", loadResource("BlockChainTest.avaj"));
	}

	@Test
	public void testReferenceTopBlock() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("some.thing")

			.startBlock("One", "one()").last()
				.addMethod("exit()").last()
				.addBlockReference("Something", "topLevel()").any()
			.endBlock()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		addTestClassMethod("Test", loadResource("TopReference.avaj"));
	}
}
