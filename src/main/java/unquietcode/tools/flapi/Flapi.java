package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage_setReturnType_setStartingMethodName;
import unquietcode.tools.flapi.builder.DescriptorGenerator;

/**
 * @author Ben Fagin
 * @version 05-11-2012
 *
 * From here you can reach the world.
 */
public class Flapi {

	/**
	 * Shortcut to build a new descriptor.
	 *
	 * @return a new {@link unquietcode.tools.flapi.builder.DescriptorBuilder}
	 */
	public static DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage_setReturnType_setStartingMethodName<Descriptor> builder() {
		return DescriptorGenerator.create(new DescriptorHelperImpl());

	}
}
