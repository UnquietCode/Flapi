package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder_setPackage extends ImplDescriptorBuilder<DescriptorBuilder_setPackage> implements DescriptorBuilder_setPackage {
	ImplDescriptorBuilder_setPackage(DescriptorHelper helper) {
		super(helper);
	}

	@Override
	public DescriptorBuilder<DescriptorBuilder> setPackage(String packageName) {
		_helper.setPackage(packageName);
		return new ImplDescriptorBuilder<DescriptorBuilder>(_helper);
	}
}
