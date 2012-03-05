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

				.startBlock("Method", "addMethod(String methodSignature)").any()
					.addMethod("once()").only()
					.addMethod("any()").only()
					.addMethod("first()").only()
					.addMethod("last()").only()
					.addMethod("atLeast(int num)").only()
					.addMethod("atMost(int num)").only()
					.addMethod("between(int atLeast, int atMost)").only()
				.endBlock()

				.startBlock("Block", "startBlock(String blockName, String methodSignature)").any()
					.addMethod("addBlockReference(String blockName, String methodSignature)").any()

					.addBlockReference("Block", "startBlock(String blockName, String methodSignature)").any()
					.addBlockReference("Method", "addMethod(String methodSignature)").any()
				.endBlock("endBlock()")

				.build()
			;

		System.out.println("");
	}
}