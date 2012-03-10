package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.outline.DescriptorOutline;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class Descriptor {
	final DescriptorOutline _descriptor;
	
	public Descriptor(DescriptorHelper helper) {
		if (!(helper instanceof DescriptorHelperImpl)) {
			throw new RuntimeException("Wrong helper instance! (this is an internal error)");
		}
		
		_descriptor = ((DescriptorHelperImpl) helper).descriptor;
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
