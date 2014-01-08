package unquietcode.tools.flapi.runtime;

/**
 * @author Ben Fagin
 * @version 07-11-2012
 */
public enum TransitionType {
	Recursive,      // goes back to itself
	Lateral,        // goes to a version of itself minus a method
	Terminal,       // exits the graph
	Ascending,      // moves upwards by 1 step
}
