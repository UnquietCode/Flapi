package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.DescriptorHelper;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class Descriptor {
	final DescriptorData _descriptor;
	
	public Descriptor(DescriptorHelper helper) {
		if (!(helper instanceof ImplDescriptorHelper)) {
			throw new RuntimeException("Wrong helper instance! (this is an internal error)");
		}
		
		_descriptor = ((ImplDescriptorHelper) helper).descriptor;
		checkDescriptor();
	}

	// TODO methods for file writing, stream writing
	public void writeCodeModel() {
		CodeGenerator2 generator = new CodeGenerator2();
		generator.generateCodeModel(_descriptor);
	}
	
	private void checkDescriptor() {
		// TODO
	}
}
