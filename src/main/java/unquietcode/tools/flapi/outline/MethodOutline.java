package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class MethodOutline implements Comparable<MethodOutline> {
	public Integer minOccurrances;
	public Integer maxOccurrances;
	public String methodSignature;

	public final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();
	
	
	public boolean isTerminal = false;


	
	
	public String returnType() {
		MethodParser parsed = new MethodParser(methodSignature);
		return parsed.returnType;
	}
	
	
	public boolean isRequired() {
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
	
	
	public static class PathSegment {
		public BlockOutline first;
		public BlockOutline second;
	}
}
