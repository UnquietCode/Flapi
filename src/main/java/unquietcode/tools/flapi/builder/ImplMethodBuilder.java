package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplMethodBuilder<_ReturnType> implements MethodBuilder<_ReturnType> {
	protected final MethodHelper _helper;
	protected final _ReturnType _returnValue;
	
	public ImplMethodBuilder(MethodHelper helper, _ReturnType returnValue) {
		_helper = helper;
		_returnValue = returnValue;
	}
	
	@Override
	public _ReturnType once() {
		_helper.once();
		return _returnValue;
	}

	@Override
	public _ReturnType any() {
		_helper.any();
		return _returnValue;
	}

	@Override
	public _ReturnType only() {
		_helper.only();
		return _returnValue;
	}

	@Override
	public _ReturnType atLeast(int num) {
		_helper.atLeast(num);
		return _returnValue;
	}

	@Override
	public _ReturnType atMost(int num) {
		_helper.atMost(num);
		return _returnValue;
	}

	@Override
	public _ReturnType between(int atLeast, int atMost) {
		_helper.between(atLeast, atMost);
		return _returnValue;
	}
}
