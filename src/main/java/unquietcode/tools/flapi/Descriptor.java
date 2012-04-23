package unquietcode.tools.flapi;

import com.sun.codemodel.JCodeModel;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.generator.DescriptorGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.outline.DescriptorOutline;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class Descriptor {
	final DescriptorOutline _descriptor;
	
	public Descriptor(DescriptorHelper helper) {
		if (!(helper instanceof DescriptorHelperImpl)) {
			throw new RuntimeException("Wrong helper instance! (this is an internal error)");
		}
		
		_descriptor = ((DescriptorHelperImpl) helper).outline;
		checkDescriptor();
	}

	// TODO methods for file writing, stream writing

	public void writeCodeModel() {
		DescriptorGenerator generator = new DescriptorGenerator(_descriptor);
		JCodeModel model = generator.generate();
		
		CodeWriter.writeToConsole(model);
		CodeWriter.writeToDirectory(model, "/Users/bfagin/Desktop/lmbuilder");
	}
	
	private void checkDescriptor() {
		// TODO
	}
}
