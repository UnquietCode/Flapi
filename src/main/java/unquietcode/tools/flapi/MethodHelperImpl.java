package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class MethodHelperImpl implements MethodHelper {
	final MethodOutline method;

	MethodHelperImpl() {
		this(new MethodOutline());
	}

	MethodHelperImpl(MethodOutline method) {
		this.method = method;
	}
	
	@Override
	public void once() {
		method.minOccurrences = 0;
		method.maxOccurrences = 1;
	}

	@Override
	public void any() {
		method.minOccurrences = 0;
		method.maxOccurrences = -1;
	}

	@Override
	public void last() {
		method.minOccurrences = 0;
		method.maxOccurrences = 1;
		method.isTerminal(true);
	}

	@Override
	public void atLeast(int num) {
		if (num < 0) {
			throw new RuntimeException("must have at least >= 0");
		}

		method.minOccurrences = num;
	}

	@Override
	public void atMost(int num) {
		if (num <= 0) {
			throw new RuntimeException("must have at least > 0");
		}

		method.maxOccurrences = num;
	}

	@Override
	public void between(int atLeast, int atMost) {
		atMost(atMost);
		atLeast(atLeast);
	}

	@Override
	public void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(method));
	}
}
