/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.runtime.TransitionType;

/**
 * @author Ben Fagin
 * @version 08-15-2012
 */
public class AscendingTransition extends Transition {
	private final boolean isRequired;

	public AscendingTransition(boolean isRequired) {
		super(TransitionType.Ascending);
		this.isRequired = isRequired;
	}

	public boolean isRequired() {
		return isRequired;
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		AscendingTransition copy = new AscendingTransition(isRequired);
		basicCopy(copy);
		return copy;
	}
}
