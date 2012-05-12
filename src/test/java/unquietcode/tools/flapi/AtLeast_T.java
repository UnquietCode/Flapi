package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.builder.DescriptorGenerator;

/**
 * @author Ben Fagin
 * @version 04-28-2012
 */
public class AtLeast_T {

	/*
		A small test to ensure that exceptions are thrown when a minimum invocation
		is not reached.
	 */
	@Test(expected = MinimumInvocationsException.class)
	public void testAtLeast() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("some.package")
			.setStartingMethodName("create")
			.setDescriptorName("something")
			//.setReturnType(Void.class)  // should trigger a failure
			.addMethod("done()").last()
		.build();
	}
}
