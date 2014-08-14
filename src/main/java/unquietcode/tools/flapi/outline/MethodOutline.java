/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.outline;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class MethodOutline extends MethodInfo implements Outline {
	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();
	private boolean isTerminal = false;
	private String returnType;
	private Integer group;
	private Integer trigger;

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
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

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getTrigger() {
		return trigger;
	}

	public void setTrigger(Integer trigger) {
		this.trigger = trigger;
	}

	/*
		A required method must be present on all interfaces.
	 */
	public boolean isRequired() {

		// it's not triggered, and either it's not dynamic
		// or it is terminal
		return trigger == null && (getMaxOccurrences() == -1 || isTerminal);
	}

	public MethodOutline copy()  {
		MethodOutline clone = new MethodOutline();
		super.copy(clone);

		clone.isTerminal = isTerminal;
		clone.blockChain.addAll(blockChain);
		clone.returnType = returnType;
		clone.group = group;
		clone.trigger = trigger;

		return clone;
	}
}
