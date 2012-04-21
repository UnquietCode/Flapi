package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;

import java.util.List;

/**
 * @author Ben Fagin (Nokia)
 * @version 04-21-2012
 */
public class ImplDescriptorBuilder$base {
	protected final DescriptorHelper _helper;
	protected final Descriptor _returnValue;

	ImplDescriptorBuilder$base(DescriptorHelper helper) {
		_helper = helper;
		_returnValue = new Descriptor(_helper);
	}

	public Descriptor build() {
		return _returnValue;
	}

	public MethodBuilder addMethod(String methodSignature) {
		MethodHelper helper = _helper.addMethod(methodSignature);
		return new ImplMethodBuilder(helper, this);
	}

}
