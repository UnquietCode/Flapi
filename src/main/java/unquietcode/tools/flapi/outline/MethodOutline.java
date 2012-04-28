package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class MethodOutline implements Comparable<MethodOutline> {
	public Integer minOccurrences;
	public Integer maxOccurrences;
	public String methodSignature;
	private boolean isTerminal = false;

	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();
	
	
	public List<BlockOutline> getBlockChain() {
		return blockChain;
	}
	
	public boolean isTerminal() {
		return isTerminal;
	}
	
	public void isTerminal(boolean value) {
		isTerminal = value;
	}

	public String returnType() {
		MethodParser parsed = new MethodParser(methodSignature);
		return parsed.returnType;
	}
	
	
	public boolean isRequired() {
		return maxOccurrences == -1 || isTerminal;
	}


	public MethodOutline copy()  {
		MethodOutline clone = new MethodOutline();
		clone.minOccurrences = minOccurrences;
		clone.maxOccurrences = maxOccurrences;
		clone.methodSignature = methodSignature;
		clone.isTerminal = isTerminal;
		clone.blockChain.addAll(blockChain);

		return clone;
	}

	@Override
	public String toString() {
		return methodSignature + "-" + maxOccurrences;
	}

	/*
		Used by sorted collections to provide consistent ordering.
	 */
	public @Override int compareTo(MethodOutline other) {
		return methodSignature.compareTo(other.methodSignature);
	}
}
