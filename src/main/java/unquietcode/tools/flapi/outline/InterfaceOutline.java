package unquietcode.tools.flapi.outline;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class InterfaceOutline {
	public String name;
	public final Set<MethodOutline> methods = new HashSet<MethodOutline>();







}

/*
	is it the case that having method calls everywhere fails to internalize the true
	connectedness of the application, or possibly diminishes it? A sense of trust
	but also that creates risk. so 'read only' is best. In this way, the value is
	write once.


		parent.name vs parent.getName()




		run(parent);

		run(name) {
			//
		}
*/
