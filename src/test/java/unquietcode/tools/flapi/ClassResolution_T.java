/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import org.junit.Test;

public class ClassResolution_T extends AbstractCompiledTest {

	@Test
	public void testImplicitPackage() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(Map variable)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}

	@Test
	public void testArrayType() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(byte[] variable)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());

		addTestClassMethod("Test",
			"some.thing.Something.SomethingGenerator.create(null)"
		  + "  .method(new byte[]{'a', 'b'})"
		  + ";"
		);

		testCompile();
	}
}

