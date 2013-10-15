/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi;

import org.junit.Assert;
import org.junit.Test;
import unquietcode.tools.flapi.builder.Descriptor.DescriptorGenerator;
import unquietcode.tools.flapi.helpers.DescriptorHelperImpl;

/**
 * Tests which check the build process for the appropriate errors.
 */
public class BuildChecks_T {

	@Test
	public void twoBlocksNoCollision() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Hello", "hello1()").last()
				.addMethod("end()").last()
			.endBlock()
			.startBlock("Goodbye", "hello2()").any()
				.addMethod("end()").last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void blockNameCollisionCheck_twoTopLevel() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Hello", "hello1()").any().endBlock()
			.startBlock("Hello", "hello2()").any().endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void blockNameCollisionCheck_nested() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Hello", "hello1()").any()
				.startBlock("Hello", "hello2()").any().endBlock()
			.endBlock()

		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void blockNameCollisionCheck_inBlockChain() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.addMethod("hello1()")
				.addBlockChain()
					.startBlock("Hello")
					.endBlock()
				.end()
			.any()

			.startBlock("Hello", "hello2()").any().endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void blockNameCollisionCheck_inBlockChainNested() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.addMethod("hello1()")
				.addBlockChain()
					.startBlock("Hello")
						.startBlock("Hello", "hello2()").any().endBlock()
					.endBlock()
				.end()
			.any()

		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void twoMethodsSameSignature() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Block1", "void hello()").any().endBlock()
			.startBlock("Block2", "void hello()").any().endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void unreferencedBlock() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Hello", "void hello()").any().endBlock()
			.addMethod("method()")
				.addBlockChain()
					.addBlockReference("Moo")
				.end()
			.any()
		.build();
	}

	@Test
	public void referencedBlockInChain() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.addMethod("method()")
				.addBlockChain()
					.startBlock("Hello")
						.addMethod("end()").last()
					.endBlock()
				.end()
			.last()

			.startBlock("Goodbye", "void hello2()")
				.addBlockChain()
					.addBlockReference("Hello")
				.end()
			.any()
				.addMethod("last()").last()
				.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void everyBlockHasALastMethod_noMethods() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Block", "void method()").any()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void everyBlockHasALastMethod_someMethods() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Block", "void method()").any()
				.addMethod("sometimes()").atMost(1)
				.addMethod("always()").any()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void everyBlockHasALastMethod_recursive() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("Block", "block()").any()
				.addMethod("done()").last()

				.startBlock("Nested1", "nested1()").any()
					// no last method

					.startBlock("Nested2", "nested2()").any()
						.addMethod("done()").last()
					.endBlock()
				.endBlock()
			.endBlock()
		.build();
	}

	@Test
	public void createMethodIsAValidName() {
		String[] names = new String[]{"4four", "inv*lid", "", " ", "new"};

		for (String name : names) {
			try {
				DescriptorGenerator.create(new DescriptorHelperImpl())
					.setPackage("unquietcode.something")
					.setDescriptorName("Something")
					.setStartingMethodName(name)
					.addMethod("done()").last()
				.build();

				Assert.fail("Expected an exception for name '"+name+"'.");
			} catch (DescriptorBuilderException ex) {
				// nothing
			} catch (IllegalArgumentException ex) {
				// nothing
			}
		}
	}

	@Test
	public void packageIsAValidName() {
		String[] names = new String[]{".something", ".", "my .package", "my. package", "my.new.thing"};

		for (String name : names) {
			try {
				DescriptorGenerator.create(new DescriptorHelperImpl())
					.setPackage(name)
					.setDescriptorName("Something")
					.addMethod("done()").last()
				.build();

				Assert.fail("Expected an exception for name '"+name+"'.");
			} catch (DescriptorBuilderException ex) {
				// nothing
			}
		}
	}

	@Test(expected=DescriptorBuilderException.class)
	public void invalidMethodName() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("something")
			.setDescriptorName("Something")
			.addMethod("2hot2handle()").last()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void methodNameIsKeyword() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("something")
			.setDescriptorName("Something")
			.addMethod("continue()").last()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void invalidParameterName() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("something")
			.setDescriptorName("Something")
			.addMethod("name(String inv#lid)").last()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void emptyDescriptorIsRejected() {
		Flapi.builder()
			.setPackage("something")
			.setDescriptorName("Something")
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void emptyBlockIsRejected() {
		Flapi.builder()
			.setPackage("something")
			.setDescriptorName("Something")

			.startBlock("anon()").any()

			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void emptyBlockWithBlockChainIsRejected() {
		Flapi.builder()
			.setPackage("something")
			.setDescriptorName("Something")

			.startBlock("anon1()")
				.addBlockChain()
					.startBlock("Anon2")
						.addMethod("done()").last()
					.endBlock()
				.end()
			.last()

			.endBlock()
		.build();
	}

	@Test
	public void selfReferencedBlock() {
		Flapi.builder()
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("BlockB", "void hello()").last()
				.addMethod("void recurse()")
					.addBlockChain()
						.addBlockReference("BlockB")
					.end()
				.last()
				.addMethod("escape()").last()
			.endBlock()
		.build();
	}

	@Test
	public void implicitTerminal() {
		Flapi.builder()
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("BlockB", "void hello()").atMost(1)
				.addMethod("end()").last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void infiniteLoop() {
		Flapi.builder()
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")

			.startBlock("BlockA", "void hello()").atMost(1)
				.addMethod("void recurse()")
					.addBlockChain()
						.addBlockReference("BlockA")
					.end()
				.last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void infiniteLoopForSingleStateChain() {
		Flapi.builder()
			.setPackage("unquietcode.tools.flapi.test.infinite")
			.setDescriptorName("Looping")

			.startBlock("BlockA", "blockA()").last()
				.addBlockReference("BlockA", "blockARef()").last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void infiniteLoopForMultipleStateChain_1() {
		Flapi.builder()
			.setPackage("unquietcode.tools.flapi.test.infinite")
			.setDescriptorName("Looping")

			.startBlock("BlockA", "blockA()").any()
				.addMethod("nothing()").last()
			.endBlock()

			.startBlock("BlockB", "blockB()").last()
				.addBlockReference("BlockA", "blockARef()")
					.addBlockChain()
						.addBlockReference("BlockB")
					.end()
				.last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void infiniteLoopForMultipleStateChain_2() {
		Flapi.builder()
			.setPackage("unquietcode.tools.flapi.test.infinite")
			.setDescriptorName("Looping")

			.startBlock("BlockA", "blockA()").any()
				.addMethod("nothing()").last()
			.endBlock()

			.startBlock("BlockB", "blockB()").last()
				.addBlockReference("BlockB", "blockBRef()")
					.addBlockChain()
						.addBlockReference("BlockA")
					.end()
				.last()
			.endBlock()
		.build();
	}
}