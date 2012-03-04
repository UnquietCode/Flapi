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

			.startBlock("DescriptorHelper", "create()").any()
				.addMethod("showLog(boolean neal)").atMost(1)
				.addMethod("setPackage(String packageName)").atMost(1)
				.addMethod("anotherOption(String option)").atMost(1)

				.startBlock("Block", "startBlock(String blockName, String methodSignature)").any()
					.addMethod("addBlockReference(String blockName, String methodSignature)").any()
					.addBlockReference("Block", "startBlock(String blockName, String methodSignature)").any()

					.startBlock("Method", "addMethod(String methodSignature)").any()
						.addMethod("once()").only()
						.addMethod("any()").only()
						.addMethod("first()").only()
						.addMethod("last()").only()

						.startBlock("Method_atLeast", "atLeast(int num)").only()
							.addMethod("atMost(int num)").atMost(1)
						.endBlock()

						.startBlock("Method_atMost", "atMost(int num)").only()
							.addMethod("atLeast(int num)").atMost(1)
						.endBlock()
					.endBlock()
				.endBlock("endBlock()")
			.endBlock()

			.showLog(true)
		;
		
		builder.build();



		System.out.println(builder);
	}
}