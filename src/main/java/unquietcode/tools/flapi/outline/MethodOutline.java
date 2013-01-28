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
public class MethodOutline extends MethodInfo implements Outline {
	private boolean isTerminal = false;
	private Class returnType;
	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();


	public Class getReturnType() {
		return returnType;
	}

	public void setReturnType(Class returnType) {
		this.returnType = returnType;
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
		MethodParser parsed = new MethodParser(getMethodSignature());
		return parsed.returnType;
	}

	/*
		A required method must be present on all interfaces.
	 */
	public boolean isRequired() {
		return getMaxOccurrences() == -1 || isTerminal;
	}


	public MethodOutline copy()  {
		MethodOutline clone = new MethodOutline();
		super.copy(clone);
		clone.isTerminal = isTerminal;
		clone.blockChain.addAll(blockChain);
		clone.returnType = returnType;

		return clone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		MethodOutline that = (MethodOutline) o;

		if (isTerminal != that.isTerminal) return false;
		if (blockChain != null ? !blockChain.equals(that.blockChain) : that.blockChain != null) return false;
		if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (isTerminal ? 1 : 0);
		result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
		result = 31 * result + (blockChain != null ? blockChain.hashCode() : 0);
		return result;
	}
}
