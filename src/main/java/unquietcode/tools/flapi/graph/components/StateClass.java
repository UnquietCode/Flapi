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

package unquietcode.tools.flapi.graph.components;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author Ben Fagin
 * @version 07-10-2012
 */
public class StateClass {
	protected Set<Transition> transitions = new HashSet<Transition>();
	private StateClass parent;
	private StateClass base;
	private String name;
	private boolean isTopLevel = false;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		if (base != null) {
			return base.getName();
		}

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

	public Set<Transition> getTransitions() {
		Set<Transition> retval = new HashSet<Transition>(transitions);

		if (base != null) {
			for (Transition transition : base.transitions) {
				Transition t = transition.copy();
				t.setOwner(this);
				retval.add(t);

				// replace any instances of the base state with ourselves
				List<StateClass> stateChain = t.getStateChain();
				for (int i=0; i < stateChain.size(); ++i) {
					StateClass state = stateChain.get(i);
					if (state == base) {
						stateChain.set(i, this);
					}
				}
			}
		}

		return retval;
	}

	public void replaceTransition(Transition theOldOne, Transition theNewOne) {
		checkNotNull(theOldOne);
		checkNotNull(theNewOne);
		checkState(transitions.contains(theOldOne), "the transition to replace was not found");

		transitions.remove(theOldOne);
		addTransitions(theNewOne);
	}

	public final void addTransitions(Transition...transitions) {
		for (Transition transition : transitions) {
			checkState(transition.getOwner() == null, "Transition should not have an owner initially.");
			transition.setOwner(this);
			this.transitions.add(transition);
		}
	}

	public final StateClass getBaseState() {
		return base;
	}

	public final void setBaseState(StateClass base) {
		this.base = base;
	}

	public final void setParentState(StateClass parent) {
		this.parent = parent;
	}

	public final StateClass getParentState() {
		return parent;
	}

	public boolean isLateral(StateClass other) {
		return this == other                                   // true of we're the same object
			|| this == other.getBaseState()                    // true if we're the base state
			|| this.getBaseState() == other                    // true if it's our base state
			|| this.getBaseState() == other.getBaseState()     // true if we share the same base state
		;
	}
}
