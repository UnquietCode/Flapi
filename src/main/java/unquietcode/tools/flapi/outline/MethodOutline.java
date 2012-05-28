/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class MethodOutline implements Outline, Comparable<MethodOutline> {
	public Integer minOccurrences;
	public Integer maxOccurrences;
	private String methodSignature;
	private boolean isTerminal = false;
	private Class intermediateResult;
	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();


	public Class getIntermediateResult() {
		return intermediateResult;
	}

	public void setIntermediateResult(Class intermediateResult) {
		this.intermediateResult = intermediateResult;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature.trim();
	}
	
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
