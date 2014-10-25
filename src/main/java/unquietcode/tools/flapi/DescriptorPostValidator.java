/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi;

import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.AscendingTransition;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.TerminalTransition;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.runtime.TransitionType;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

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
		AtomicBoolean value = checkForBlocksWithNoEnd(graph, new IdentityHashMap<StateClass, AtomicBoolean>());

		if (!value.get()) {
			throw reportNoTerminalMethod(graph);
		}
	}

	private AtomicBoolean checkForBlocksWithNoEnd(StateClass state, final Map<StateClass, AtomicBoolean> seen) {
		if (seen.containsKey(state)) { return seen.get(state); }
		final AtomicReference<AtomicBoolean> valid = new AtomicReference<AtomicBoolean>(new AtomicBoolean(false));
		final AtomicReference<AtomicBoolean> terminal = new AtomicReference<AtomicBoolean>(new AtomicBoolean(false));
		final AtomicReference<AtomicBoolean> recursive = new AtomicReference<AtomicBoolean>(new AtomicBoolean(false));

		// check for recursion
		for (Transition transition : state.getTransitions()) {
			transition.accept(new TransitionVisitor.$() {
				public @Override void visit(TerminalTransition transition) {
					handle(transition, true);
				}

				public @Override void visit(AscendingTransition transition) {
					handle(transition, transition.isRequired());
				}

				// check for infinite loops
				void handle(Transition transition, boolean value) {

					// for every state in the state chain
					for (StateClass step : transition.getStateChain()) {

						// if it is parallel to us, then it's recursive
						if (step.isLateral(transition.getOwner())) {
							recursive.get().set(true);
							return;
						}
					}

					terminal.get().set(terminal.get().get() || value);
				}
			});
		}

		// If there was a good method, AND it's recursive, no sweat,
		// but if there was ONLY a recursive method, then that's an
		// indication of an infinite loop.
		if (terminal.get().get() == false && recursive.get().get() == true) {
			throw new DescriptorBuilderException("Infinite loop detected for block '"+state.getName()+"'.");
		}

		valid.set(terminal.get());
		seen.put(state, terminal.get());

		// check every other transition to ensure that they can find a terminal
		for (Transition transition : state.getTransitions()) {
			final TransitionType transitionType = transition.getType();

			transition.acceptForTraversal(new GenericVisitor<StateClass>() {
				public void visit(StateClass next) {
					AtomicBoolean nextIsTerminal = checkForBlocksWithNoEnd(next, seen);

					if (transitionType != TransitionType.Recursive) {
						valid.get().set(valid.get().get() || nextIsTerminal.get());
					}
				}
			});
		}

		if (valid.get().get() != true) {
			throw reportNoTerminalMethod(state);
		}

		// return terminal because that's what we are really tracking
		return terminal.get();
	}

	private static DescriptorBuilderException reportNoTerminalMethod(StateClass state) throws DescriptorBuilderException {
		throw new DescriptorBuilderException("Encountered a block with no terminal method: " + state.getName());
	}
}