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
public class RecursiveTransition extends Transition {
	public RecursiveTransition() {
		super(TransitionType.Recursive);
	}


	/**
	 * Alias for {@link #getOwner()}.
	 * @return the {@link StateClass} for this transition
	 */
	public StateClass getSelf() {
		return getOwner();
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		RecursiveTransition copy = new RecursiveTransition();
		basicCopy(copy);
		return copy;
	}
}
