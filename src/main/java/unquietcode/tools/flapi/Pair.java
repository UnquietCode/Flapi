/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

/**
 * @author Ben Fagin
 */
public class Pair<A,B> {
	public final A first;
	public final B second;


	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
}
