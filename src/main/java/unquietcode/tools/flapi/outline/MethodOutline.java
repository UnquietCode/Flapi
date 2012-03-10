package unquietcode.tools.flapi.outline;


/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class MethodOutline implements Comparable<MethodOutline> {
	public Integer minOccurrances;
	public Integer maxOccurrances;
	public String methodSignature;

	boolean isTerminal = false;

	boolean isRequired() {
		return maxOccurrances == -1 || isTerminal;
	}










	public MethodOutline copy()  {
		MethodOutline clone = new MethodOutline();
		clone.minOccurrances = minOccurrances;
		clone.maxOccurrances = maxOccurrances;
		clone.methodSignature = methodSignature;

		return clone;
	}

	public @Override int compareTo(MethodOutline other) {
		return methodSignature.compareTo(other.methodSignature);
	}
}
