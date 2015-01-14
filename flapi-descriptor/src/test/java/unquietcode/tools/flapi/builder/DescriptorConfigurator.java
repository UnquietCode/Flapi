package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;

/**
 * Smaller descriptor for customizing the creation of a
 * descriptor from an annotated class.
 *
 * @author Benjamin Fagin
 * @version 08-03-2014
 */
public class DescriptorConfigurator extends BaseDescriptor {

	@Override
	public Descriptor descriptor() {
		Descriptor builder = baseBuilder()
			.setPackage("unquietcode.tools.flapi.configurator")
			.setDescriptorName("DescriptorConfigurator")
		.build();

		return builder;
	}
}