package unquietcode.tools.flapi.examples.calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationHelper;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 */
public class CalculationHelperImpl implements CalculationHelper {
	private final AtomicReference<BigInteger> _value;

	public CalculationHelperImpl(AtomicReference<BigInteger> value) {
		_value = value;
	}

	@Override
	public void plus(int value) {
		_value.set(_value.get().add(BigInteger.valueOf(value)));
	}

	@Override
	public void minus(int value) {
		_value.set(_value.get().subtract(BigInteger.valueOf(value)));
	}

	@Override
	public void times(int value) {
		_value.set(_value.get().multiply(BigInteger.valueOf(value)));
	}

	@Override
	public void divide(int value) {
		_value.set(_value.get().divide(BigInteger.valueOf(value)));
	}

	@Override
	public void mod(int value) {
		_value.set(_value.get().mod(BigInteger.valueOf(value)));
	}

	@Override
	public void power(int value) {
		_value.set(_value.get().pow(value));
	}

	@Override
	public void abs() {
		_value.set(_value.get().abs());
	}

	@Override
	public AtomicReference equals() {
		return _value;
	}
}
