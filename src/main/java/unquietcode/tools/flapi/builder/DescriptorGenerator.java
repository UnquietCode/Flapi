package unquietcode.tools.flapi.builder;


import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorBuilderException;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 * @version 04-25-2012
 */
public class DescriptorGenerator {
	@SuppressWarnings("unchecked")
	public static DescriptorBuilder_setPackage_showLog<Descriptor> create(DescriptorHelper helper) {
		if (helper == null) {
			throw new DescriptorBuilderException("Helper cannot be null.");
		}

		return new ImplDescriptorBuilder_setPackage_showLog(helper, helper._getReturnValue());
	}
}
