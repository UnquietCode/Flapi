package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.runtime.TransitionType;

/**
 * @author Ben Fagin
 * @version 08-15-2012
 */
public class TerminalTransition extends Transition {
	String returnType;

	public TerminalTransition() {
		super(TransitionType.Terminal);
	}

	public TerminalTransition(String returnType) {
		this();
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		TerminalTransition copy = new TerminalTransition();
		basicCopy(copy);
		copy.returnType = this.returnType;
		return copy;
	}
}
