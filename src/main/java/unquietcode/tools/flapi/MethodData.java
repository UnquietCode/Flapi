package unquietcode.tools.flapi;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/
public class MethodData implements Comparable<MethodData> {
	Integer minOccurrances;
	Integer maxOccurrances;
	String methodSignature;

	boolean isPrelimiary = false;
	boolean isTerminal = false;
	
	boolean isRequired() {
		return maxOccurrances == -1 || isTerminal;
	}
	
	public MethodData copy()  {
		MethodData clone = new MethodData();
		clone.minOccurrances = minOccurrances;
		clone.maxOccurrances = maxOccurrances;
		clone.methodSignature = methodSignature;
		
		return clone;
	}

	public @Override int compareTo(MethodData other) {
		return methodSignature.compareTo(other.methodSignature);
	}
}
