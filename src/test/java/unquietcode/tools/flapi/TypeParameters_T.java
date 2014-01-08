package unquietcode.tools.flapi;

import org.junit.Test;

public class TypeParameters_T extends AbstractCompiledTest {

	@Test
	public void testTypeParameters() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(List<String> list, int b)").last()
			.addMethod("method(Map<String, Integer> map, int b)").last()
			.addMethod("method(int a)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void testInvalidTypeParameter() {
		Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(Map<String, int> map)").last()
		.build();
	}

	@Test
	public void testNestedTypeParameters() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("method(Map<String, Set<String>> map, int b)").last()
			.addMethod("method(int a)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}

	@Test
	public void testReturnTypeParameter() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("List<String> method(int number)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}

	@Test
	public void testVarargTypeParameter() {
		Descriptor descriptor = Flapi.builder()
			.setPackage("some.thing")
			.setDescriptorName("Something")
			.addMethod("void methodA(List<String>...list)").last()
			.addMethod("methodB(List<String>...list)").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}
}

