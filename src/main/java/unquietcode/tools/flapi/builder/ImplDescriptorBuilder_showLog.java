package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplDescriptorBuilder_showLog extends ImplDescriptorBuilder<DescriptorBuilder_showLog> implements DescriptorBuilder_showLog {
	ImplDescriptorBuilder_showLog(DescriptorHelper helper) {
		super(helper);
	}

	@Override
	public DescriptorBuilder<DescriptorBuilder> showLog(boolean value) {
		_helper.showLog(value);
		return new ImplDescriptorBuilder<DescriptorBuilder>(_helper);
	}
}
