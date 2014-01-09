/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.outline;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class MethodOutline extends MethodInfo implements Outline {
	private boolean isTerminal = false;
	private String returnType;
	private final List<BlockOutline> blockChain = new ArrayList<BlockOutline>();
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
