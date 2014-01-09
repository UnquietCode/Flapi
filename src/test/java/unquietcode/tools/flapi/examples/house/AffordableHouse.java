/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class AffordableHouse extends House {
	@Override
	String getCost() {
		return "$75,000.00";
	}
}
