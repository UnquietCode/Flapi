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
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 */
public class CalculatorHelperImpl implements Calculator {
	private final CalculatorBuilderExample.Result value = new CalculatorBuilderExample.Result();

	CalculatorHelperImpl() { }

	@Override
	public void $(int startingValue, AtomicReference<Calculation> _helper1) {
		_helper1.set(new CalculationHelperImpl(value));
		value.set(BigInteger.valueOf(startingValue));
	}
}