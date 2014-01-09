/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 */
public class GroupedMethods_T extends AbstractCompiledTest {

	@Test
	public void testGroupedAnyMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("regular()").any()
			.addMethod("terminal()").last()

			.addMethod("required1()").any(1)
			.addMethod("optional1()").atMost(2, 1)

			.addMethod("required2A()").any(2)
			.addMethod("required2B()").any(2)
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();

		testCompile("GroupedTest1.avaj");
		testCompile("GroupedTest2.avaj");
	}
}