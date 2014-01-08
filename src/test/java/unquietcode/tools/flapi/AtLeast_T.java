package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.builder.Descriptor.DescriptorGenerator;
import unquietcode.tools.flapi.helpers.DescriptorHelperImpl;
import unquietcode.tools.flapi.runtime.ExpectedInvocationsException;

/**
 * @author Ben Fagin
 * @version 04-28-2012
 */
public class AtLeast_T {

	/*
		A small test to ensure that exceptions are thrown when a minimum invocation
		is not reached.
	 */
	@Test(expected = ExpectedInvocationsException.class)
	public void testAtLeast() {
		DescriptorGenerator.create(new DescriptorHelperImpl())
			.setPackage("some.package")
			.setStartingMethodName("create")
			//.setDescriptorName("something") // should trigger a failure
			.addMethod("done()").last()
		.build();
	}
}
