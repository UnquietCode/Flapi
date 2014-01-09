/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph.components;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author Ben Fagin
 * @version 07-10-2012
 */
public class StateClass {
	protected Set<Transition> transitions = new HashSet<Transition>();
	private Object blockMarker;
	private String name;
	private boolean isTopLevel = false;


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isTopLevel() {
		return isTopLevel;
	}

	public void setIsTopLevel() {
		isTopLevel = true;
	}


	// for debugging porpoises, mostly
	@Override
	public String toString() {
		String str = "";
		for (Transition transition : getTransitions()) {
			str += transition.getMethodSignature() + " | ";
		}

		return str;
	}

	public TreeSet<Transition> getTransitions() {
		return new TreeSet<Transition>(transitions);
	}

//	public void replaceTransition(Transition theOldOne, Transition theNewOne) {
//		checkNotNull(theOldOne);
//		checkNotNull(theNewOne);
//		checkState(transitions.contains(theOldOne), "the transition to replace was not found");
//
//		transitions.remove(theOldOne);
//		addTransitions(theNewOne);
//	}

	public final void addTransitions(Transition...transitions) {
		for (Transition transition : transitions) {
			checkState(transition.getOwner() == null, "Transition should not have an owner initially.");
			transition.setOwner(this);
			this.transitions.add(transition);
		}
	}

	public final Object getBlockMarker() {
		return blockMarker;
	}

	public final void setBlockMarker(Object marker) {
		this.blockMarker = marker;
	}

	public boolean isLateral(StateClass other) {
		return this == other                                    // true of we're the same object
			|| this.getBlockMarker() == other.getBlockMarker()  // true if we are from the same block
		;
	}
}
