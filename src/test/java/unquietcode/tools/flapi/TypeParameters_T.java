/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

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

