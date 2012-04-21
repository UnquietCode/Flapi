package unquietcode.tools.flapi.builder;


import unquietcode.tools.flapi.Descriptor;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class DescriptorGenerator {
	public static DescriptorBuilder_setPackage_showLog<Descriptor> create(String name, String method, DescriptorHelper helper) {
		if (method == null || (method = method.trim()).isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		
		if (helper == null) {
			throw new IllegalArgumentException("Helper cannot be null.");
		}
		
		helper._setDescriptorName(name);
		helper._setDescriptorMethod(method);

		return new ImplDescriptorBuilder_setPackage_showLog(helper);
	}

	public static DescriptorBuilder_setPackage_showLog<Descriptor> create(String name, DescriptorHelper helper) {
		return create(name, "create", helper);
	}	
}
