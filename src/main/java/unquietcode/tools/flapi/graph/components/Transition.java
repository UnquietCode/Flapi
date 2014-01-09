/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.outline.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 07-10-2012
 */
public abstract class Transition implements Comparable<Transition> {
	private MethodInfo methodInfo = new MethodInfo();
	private final List<StateClass> stateChain = new ArrayList<StateClass>();
	private StateClass owner;
	private final TransitionType type;

	public abstract void accept(TransitionVisitor visitor);

	public void acceptForTraversal(GenericVisitor<StateClass> visitor) {
		for (StateClass stateClass : stateChain) {
			visitor.visit(stateClass);
		}
	}

	protected Transition(TransitionType type) {
	    this.type = type;
	}

	public TransitionType getType() {
		return type;
	}

	public List<StateClass> getStateChain() {
		return stateChain;
	}

	@Override
	public int compareTo(Transition other) {
		return methodInfo.getMethodSignature().compareTo(other.info().getMethodSignature());
	}

	void setOwner(StateClass owner) {
		this.owner = owner;
	}

	public StateClass getOwner() {
		return owner;
	}

	public String getMethodSignature() {
		return methodInfo.getMethodSignature();
	}

	public MethodInfo info() {
		return methodInfo;
	}

	public void setMethodInfo(MethodInfo methodInfo) {
		this.methodInfo = methodInfo;
	}

	public abstract Transition copy();

	protected void basicCopy(Transition copy) {
		copy.methodInfo = this.methodInfo.copy();
		copy.stateChain.addAll(this.stateChain);
	}
}
