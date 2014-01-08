package unquietcode.tools.flapi.examples.house;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class ExpensiveHouse extends House{
	@Override
	String getCost() {
		return "$2,100,000.00";
	}
}
