/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 04-10-2013
 */
public class TriggeredMethods_T extends AbstractCompiledTest {

	@Test
	public void testBasicTriggeredMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("dynamic()").atMost(1)
			.addMethod("dynamicWithGroup()").atMost(1, 1)
			.addMethod("triggered()").after(1).atLeast(1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile("TriggeredTest2.avaj");
	}

	@Test
	public void testTriggeredAnyMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("required()").any()
			.addMethod("requiredWithGroup()").any(1)
			.addMethod("triggered()").after(1).atMost(1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());

		addTestClassMethod("Test", loadResource("TriggeredTest1.avaj"));
		testCompile();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void testTriggeringSameGroup() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("triggered()").after(1).atMost(2, 1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToStream(BlackHoleStream.$);
	}
}