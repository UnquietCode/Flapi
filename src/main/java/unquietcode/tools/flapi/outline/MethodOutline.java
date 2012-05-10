package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;

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

	/*
		A terminal method will return out of the class, though
		may go through a block chain first.
	 */
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

	/*
		A required method must be present on all interfaces.
	 */
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

	public String getMethodKey() {
		StringBuilder sb = new StringBuilder();
		MethodParser parser = new MethodParser(methodSignature);
		sb.append(parser.methodName).append("$");
		boolean first = true;

		for (Pair<String, String> param : parser.params) {
			if (!first) { sb.append("$"); }
			else { first = false; }

			sb.append(param.first).append("_").append(param.second);
		}

		return sb.toString();
	}

	/*
		Used by sorted collections to provide consistent ordering.
	 */
	public @Override int compareTo(MethodOutline other) {
		return methodSignature.compareTo(other.methodSignature);
	}
}
