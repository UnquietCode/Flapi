/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import java.math.BigInteger;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 */
public class CalculationHelperImpl implements Calculation {
	private final CalculatorBuilderExample.Result _value;

	public CalculationHelperImpl(CalculatorBuilderExample.Result value) {
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
	public CalculatorBuilderExample.Result equals() {
		return _value;
	}
}