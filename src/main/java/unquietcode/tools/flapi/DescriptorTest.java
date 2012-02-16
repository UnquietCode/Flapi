package unquietcode.tools.flapi;


/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public class DescriptorTest {

	public static void main(String[] args) {
		DescriptorBuilder builder =

		DescriptorHelper.create()
			.setPackage("dna.methylated")
			//.showLog(true)
			.anotherOption("alpha")

			.startBlock("DescriptorBuilder", "create()").any()
				.addMethod("showLog(boolean neal)").atMost(1)
				.addMethod("setPackage(String packageName)").atMost(1)
				.addMethod("anotherOption(String option)").atMost(1)

				.startBlock("Block", "startBlock(String blockName)").any()
					.addMethod("addBlockReference(String blockName, String methodSignature)").any()
					.addBlockReference("Block", "addBlockReference(String blockName)").any()

					.startBlock("Method", "addMethod(String methodSignature)").any()
						.addMethod("once").once()
						.addMethod("any").once()
						.addMethod("first").once()
						.addMethod("last").once()

						.startBlock("Method_atLeast", "atLeast(int num)").once()
							.addMethod("atMost(int num)").atMost(1)
						.endBlock()

						.startBlock("Method_atMost", "atMost(int num)").once()
							.addMethod("atLeast(int num)").atMost(1)
						.endBlock()
					.endBlock()
				.endBlock("endBlock()")
			.endBlock()

			.showLog(true)
		;

		System.out.println(builder);
	}
}