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

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

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
		checkForBlocksWithNoEnd(
			graph,
			Collections.newSetFromMap(new IdentityHashMap<StateClass, Boolean>()),
			new ObjectWrapper<Boolean>(false)
		);
	}

	private void checkForBlocksWithNoEnd(
		StateClass state, final Set<StateClass> seen, final ObjectWrapper<Boolean> valid
	){
		if (seen.contains(state)) {
			return;
		} else {
			seen.add(state);
		}

		// check this state's transitions
		for (Transition transition : state.getTransitions()) {
			if (transition instanceof RecursiveTransition) {
				continue;
			}

			transition.accept(new TransitionVisitor.$() {
				public @Override void visit(TerminalTransition transition) {
					valid.set(true);
				}

				public @Override void visit(AscendingTransition transition) {
					valid.set(true);
				}
			});

			// check every other transition to ensure that they can find a terminal
			transition.acceptForTraversal(new GenericVisitor<StateClass>() {
				public void visit(StateClass next) {
					checkForBlocksWithNoEnd(next, seen, new ObjectWrapper<Boolean>(false));

					// If there is at least one transition, and there are no
					// dead ends, then there must be at least one end,
					// so if we reach here then it is valid.
					valid.set(true);
				}
			});
		}

		if (valid.get() != true) {
			throw new DescriptorBuilderException("Encountered a block with no terminal method: " + state.getName());
		}
	}
}