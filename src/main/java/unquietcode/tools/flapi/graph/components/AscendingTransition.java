package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.runtime.TransitionType;

/**
 * @author Ben Fagin
 * @version 08-15-2012
 */
public class AscendingTransition extends Transition {
	private final boolean isOptional;

	public AscendingTransition(boolean isOptional) {
		super(TransitionType.Ascending);
		this.isOptional = isOptional;
	}

	public boolean isOptional() {
		return isOptional;
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		AscendingTransition copy = new AscendingTransition(isOptional);
		basicCopy(copy);
		return copy;
	}
}
