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

package unquietcode.tools.flapi;

import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.*;
import unquietcode.tools.flapi.support.ObjectWrapper;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 08-25-2012
 *
 * Validates a descriptor to ensure that minimum requirements are met.
 * The "post" validator works on the graph.
 */
public class DescriptorPostValidator {
	private final StateClass graph;

	public DescriptorPostValidator(StateClass graph) {
		this.graph = graph;
	}

	public void validate() {
		checkForBlocksWithNoEnd(graph, new IdentityHashMap<StateClass, Boolean>());
	}

	private boolean checkForBlocksWithNoEnd(StateClass state, final Map<StateClass, Boolean> seen) {
		if (seen.containsKey(state)) { return seen.get(state); }
		final ObjectWrapper<Boolean> valid = new ObjectWrapper<Boolean>(false);
		final ObjectWrapper<Boolean> terminal = new ObjectWrapper<Boolean>(false);

		// check this state's transitions
		for (Transition transition : state.getTransitions()) {
			transition.accept(new TransitionVisitor.$() {
				public
				@Override
				void visit(TerminalTransition transition) {
					terminal.set(true);
				}

				public
				@Override
				void visit(AscendingTransition transition) {
					terminal.set(true);
				}
			});
		}

		valid.set(valid.get() || terminal.get());
		seen.put(state, terminal.get());

		// check every other transition to ensure that they can find a terminal
		for (Transition transition : state.getTransitions()) {
			final TransitionType transitionType = transition.getType();

			transition.acceptForTraversal(new GenericVisitor<StateClass>() {
				public void visit(StateClass next) {
					boolean nextIsTerminal = checkForBlocksWithNoEnd(next, seen);

					if (transitionType != TransitionType.Recursive) {
						valid.set(valid.get() || nextIsTerminal);
					}
				}
			});
		}

		// support implicit terminals
		boolean hasImplicitTerminals = state.getBaseState() != null
									 ? state.getBaseState().hasImplicitTerminal()
									 : state.hasImplicitTerminal();
		valid.set(valid.get() || hasImplicitTerminals);

		if (!valid.get().equals(true)) {
			throw new DescriptorBuilderException("Encountered a block with no terminal method: " + state.getName());
		}

		// return terminal because that's what we are really tracking
		return terminal.get();
	}
}