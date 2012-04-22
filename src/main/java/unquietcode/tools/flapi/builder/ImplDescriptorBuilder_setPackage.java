package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder_setPackage extends ImplDescriptorBuilder implements DescriptorBuilder_setPackage {
	ImplDescriptorBuilder_setPackage(DescriptorHelper helper, Object returnValue) {
		super(helper, returnValue);
	}

	@Override
	public DescriptorBuilder setPackage(String packageName) {
		_helper.setPackage(packageName);
		return this;
	}
}
