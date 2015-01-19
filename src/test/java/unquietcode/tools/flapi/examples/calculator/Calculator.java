/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import unquietcode.tools.flapi.annotations.BlockChain;
import unquietcode.tools.flapi.annotations.Last;

import java.util.concurrent.atomic.AtomicReference;

/**
* @author Ben Fagin
* @version 2014-08-05
*/
public interface Calculator {

	@Last
	void $(int startingValue, @BlockChain AtomicReference<Calculation> helper);
}
