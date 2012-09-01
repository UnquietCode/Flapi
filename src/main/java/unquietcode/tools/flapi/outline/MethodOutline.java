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
	private boolean isImplicitTerminal = false;
	private Class returnType;
	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();


	public Class getReturnType() {
		return returnType;
	}

	public void setReturnType(Class returnType) {
		this.returnType = returnType;
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

	public void setImplicitTerminal() {
		isTerminal = true;
		isImplicitTerminal = true;
	}

	public boolean isImplicitTerminal() {
		return isImplicitTerminal;
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
		clone.returnType = returnType;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MethodOutline that = (MethodOutline) o;

		if (isTerminal != that.isTerminal) return false;
		if (blockChain != null ? blockChain.size() != that.blockChain.size() : that.blockChain != null) return false;
		if (maxOccurrences != null ? !maxOccurrences.equals(that.maxOccurrences) : that.maxOccurrences != null)
			return false;
		if (methodSignature != null ? !methodSignature.equals(that.methodSignature) : that.methodSignature != null)
			return false;
		if (minOccurrences != null ? !minOccurrences.equals(that.minOccurrences) : that.minOccurrences != null)
			return false;
		if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = minOccurrences != null ? minOccurrences.hashCode() : 0;
		result = 31 * result + (maxOccurrences != null ? maxOccurrences.hashCode() : 0);
		result = 31 * result + (methodSignature != null ? methodSignature.hashCode() : 0);
		result = 31 * result + (isTerminal ? 1 : 0);
		result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
		result = 31 * result + (blockChain != null ? blockChain.hashCode() : 0);
		return result;
	}
}
