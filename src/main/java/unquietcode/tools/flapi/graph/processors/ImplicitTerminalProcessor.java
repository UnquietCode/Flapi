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

package unquietcode.tools.flapi.graph.processors;

import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.components.*;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 08-20-2012
 */
public class ImplicitTerminalProcessor implements GenericVisitor<StateClass> {
	private Set<StateClass> seen = Collections.newSetFromMap(new IdentityHashMap<StateClass, Boolean>());

	@Override
	public void visit(StateClass state) {
		if (seen.contains(state)) {
			return;
		} else {
			seen.add(state);
		}

		Map<Transition, Transition> replacements = new HashMap<Transition, Transition>();

		for (Transition transition : state.getTransitions()) {
			if (LateralTransition.class.isAssignableFrom(transition.getClass())) {
				Transition replacement = visitCandidateState((LateralTransition) transition);

				if (replacement != null) {
					replacements.put(transition, replacement);
				}
			}

			transition.acceptForTraversal(this);
		}

		for (Map.Entry<Transition, Transition> entry : replacements.entrySet()) {
			state.replaceTransition(entry.getKey(), entry.getValue());
		}
	}


	private Transition visitCandidateState(LateralTransition transition) {
		StateClass owner = transition.getOwner();

		// only apply when moving to the base state
		if (owner.getBaseState() != transition.getSibling()) {
			return null;
		}

		// only apply when this is the last occurrence of the method
		if (transition.getMaxOccurrences() != 1) {
			return null;
		}

		// only apply when there are no required methods on the base state
		if (!transition.getSibling().getTransitions().isEmpty()) {
			return null;
		}

		// At this point, we are looking at a transition which moves to a base
		// state, with no methods, and so we change the transition to a terminal.

		Transition replacement;
		if (owner.isTopLevel()) {
			replacement = new TerminalTransition(Void.class);
		} else {
			replacement = new AscendingTransition();
		}

		replacement.setMaxOccurrences(transition.getMaxOccurrences());
		replacement.setMinOccurrences(transition.getMinOccurrences());
		replacement.setMethodSignature(transition.getMethodSignature());
		replacement.getStateChain().addAll(transition.getStateChain());

		return replacement;
	}
}
