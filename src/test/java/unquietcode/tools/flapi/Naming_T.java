/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import org.junit.Test;

public class Naming_T extends AbstractCompiledTest {

	@Test
	public void test() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(int a, int b)").last()
			.addMethod("method(int a)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}
}

