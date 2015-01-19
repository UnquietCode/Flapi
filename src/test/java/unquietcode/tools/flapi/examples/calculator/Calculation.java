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

import unquietcode.tools.flapi.annotations.Any;
import unquietcode.tools.flapi.annotations.Last;

/**
* @author Ben Fagin
* @version 2014-08-05
*/
public interface Calculation {

	@Any void plus(int value);
	@Any void minus(int value);
	@Any void times(int value);
	@Any void divide(int value);
	@Any void power(int value);
	@Any void mod(int value);
	@Any void abs();

	@Last CalculatorBuilderExample.Result equals();
}