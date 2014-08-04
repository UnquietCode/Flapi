package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;

/**
 * Smaller descriptor for customizing the creation of a
 * descriptor from an annotated class.
 *
 * @author Benjamin Fagin
 * @version 08-03-2014
 */
public class DescriptorConfigurator implements DescriptorMaker {
	private static final int RETURN_TYPE_GROUP = 2;

	@Override
	public Descriptor descriptor() {

		Descriptor builder = Flapi.builder()
			.setPackage("unquietcode.tools.flapi.configurator")
			.setDescriptorName("DescriptorConfigurator")
			.enableCondensedClassNames()

			// descriptor methods

			.addMethod("setPackage(String packageName)")
				.withDocumentation("set the root package name to use for the generated classes")
			.atMost(1)

			.addMethod("setStartingMethodName(String methodName)")
				.withDocumentation("set the name of the generator's starting method (default is 'create')")
			.atMost(1)

			.addMethod("setReturnType(Class returnType)")
				.withDocumentation("set the return type for the top level descriptor (default is void)")
			.atMost(1, RETURN_TYPE_GROUP)

			.addMethod("setReturnType(String returnType)")
				.withDocumentation("set the return type for the top level descriptor (default is void)")
			.atMost(1, RETURN_TYPE_GROUP)

			.addMethod("enableCondensedClassNames()")
				.withDocumentation()
					.addContent("Allow class names to be condensed, at the cost of no longer being\n")
					.addContent("humanly readable. If your generated class names are too long to be\n")
					.addContent("compiled, you will have to use this.")
				.finish()
			.atMost(1)

			.addMethod("build()").last(Descriptor.class)
		.build();

		return builder;
	}
}