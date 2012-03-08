package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.*;
import org.junit.Test;

/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public class ModifiedDescriptorTest {

	@Test
	public void descriptorGenerator() {
		Descriptor builder =
			DescriptorGenerator.create("Descriptor", "create", new ImplDescriptorHelper())
				.setPackage("unquietcode.tools.flapi.builder")
				
				.addMethod("showLog(boolean value)").once()
				.addMethod("setPackage(String packageName)").between(1, 1)
				.addMethod("doSomething()").any()

				.startBlock("Method", "addMethod(String methodSignature)").any()
					.addMethod("once()").last()
					.addMethod("any()").last()
					.addMethod("last()").last()
					.addMethod("atLeast(int num)").last()
					.addMethod("atMost(int num)").last()
					.addMethod("between(int atLeast, int atMost)").last()
				.endBlock()

				.startBlock("Block", "startBlock(String blockName, String methodSignature)").any()
					.addBlockChain()
						.addBlockReference("Method")

					.addMethod("addBlockReference(String blockName, String methodSignature)").any()
					.addBlockReference("Method", "addMethod(String methodSignature)").any()
					.addBlockReference("Block", "startBlock(String blockName, String methodSignature)").any()
					.addMethod("endBlock()").last()

					.startBlock("BlockChain", "addBlockChain()").once()
						.addMethod("addBlockReference(String blockName)").last()
						.addBlockReference("Block", "startBlock(String blockName, String methodSignature()").last()
						.addBlockReference("BlockChain", "addBlockChain()").once()
					.endBlock()
				.endBlock()

			.build()
		;

		/*
				block_$first

				if block has a first, then the constructor will return it
				and it will return the block without that method

				so here, that means startBlock returns a MethodBuilder (the first blockRef)
				and the return type of the method builder is what we were going to return in
				the first place, which is a new block


		 */

		builder.writeCodeModel();
	}
}