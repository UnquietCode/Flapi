package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder_setPackage_showLog extends ImplDescriptorBuilder implements DescriptorBuilder_setPackage_showLog {
	ImplDescriptorBuilder_setPackage_showLog(DescriptorHelper helper) {
		super(helper);
	}

	@Override
	public DescriptorBuilder_setPackage showLog(boolean value) {
		_helper.showLog(value);
		return new ImplDescriptorBuilder_setPackage(_helper);
	}

	@Override
	public DescriptorBuilder_showLog setPackage(String packageName) {
		_helper.setPackage(packageName);
		return new ImplDescriptorBuilder_showLog(_helper);
	}
}
