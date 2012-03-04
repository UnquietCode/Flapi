package unquietcode.tools.flapi;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/
public class MethodData {
	Integer minOccurrances;
	Integer maxOccurrances;
	String methodSignature;
	BlockData parent;
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
}
