package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder_showLog extends ImplDescriptorBuilder implements DescriptorBuilder_showLog {
	ImplDescriptorBuilder_showLog(DescriptorHelper helper) {
		super(helper);
	}

	@Override
	public DescriptorBuilder showLog(boolean showLog) {
		_helper.showLog(showLog);
		return this;
	}
}
