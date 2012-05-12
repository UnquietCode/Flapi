package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.builder.DescriptorGenerator;

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
			.setReturnType(Void.class)

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
			.setReturnType(Void.class)

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
			.setReturnType(Void.class)

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
			.setReturnType(Void.class)

			.addMethod("hello1()").addBlockChain()
				.startBlock("Hello").endBlock()
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
			.setReturnType(Void.class)

			.addMethod("hello1()").addBlockChain()
				.startBlock("Hello")
					.startBlock("Hello", "hello2()").any().endBlock()
				.endBlock()
			.any()

		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void twoMethodsSameSignature() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")
			.setReturnType(Void.class)

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
			.setReturnType(Void.class)

			.startBlock("Hello", "void hello()").any().endBlock()
			.addMethod("method()").addBlockChain().addBlockReference("Moo").any()
		.build();
	}

	@Test
	public void referencedBlockInChain() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")
			.setReturnType(Void.class)

			.addMethod("method()").addBlockChain()
				.startBlock("Hello")
					.addMethod("end()").last()
				.endBlock()
			.last()

			.startBlock("Goodbye", "void hello2()")
				.addBlockChain().addBlockReference("Hello").any()
					.addMethod("last()").last()
				.endBlock()
		.build();
	}

	@Test
	public void selfReferencedBlock() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")
			.setReturnType(Void.class)

			.startBlock("BlockA", "void hello1()")
				.addBlockChain().addBlockReference("BlockA").last()

				.addMethod("something()").last()
			.endBlock()

			.startBlock("BlockB", "void hello2()").once()
				.addMethod("void recurse()").addBlockChain().addBlockReference("BlockB").last()
			.endBlock()
		.build();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void everyBlockHasALastMethod_noMethods() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("unquietcode.something")
			.setDescriptorName("Something")
			.setStartingMethodName("create")
			.setReturnType(Void.class)

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
			.setReturnType(Void.class)

			.startBlock("Block", "void method()").any()
				.addMethod("sometimes()").once()
				.addMethod("always()").any()
			.endBlock()
		.build();
	}
}





/*
    name collision error
    block referencing
    last present
 */