package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.MethodHelper;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class ImplMethodHelper implements MethodHelper {
	final MethodData method;

	ImplMethodHelper() {
		this(new MethodData());
	}

	ImplMethodHelper(MethodData method) {
		this.method = method;
	}
	
	@Override
	public void once() {
		method.minOccurrances = 0;
		method.maxOccurrances = 1;
	}

	@Override
	public void any() {
		method.minOccurrances = 0;
		method.maxOccurrances = -1;
	}

	@Override
	public void only() {
		method.minOccurrances = 0;
		method.maxOccurrances = 1;
		method.isTerminal = true;
	}

	@Override
	public void atLeast(int num) {
		if (num < 0) {
			throw new RuntimeException("must have at least >= 0");
		}

		method.minOccurrances = num;
	}

	@Override
	public void atMost(int num) {
		if (num <= 0) {
			throw new RuntimeException("must have at least > 0");
		}

		method.maxOccurrances = num;
	}

	@Override
	public void between(int atLeast, int atMost) {
		atMost(atMost);
		atLeast(atLeast);
	}
}
