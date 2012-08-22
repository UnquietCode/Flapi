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

package unquietcode.tools.flapi.graph;

import unquietcode.tools.flapi.graph.components.*;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 08-12-2012
 */
public class GraphBuilder {

	public StateClass buildGraph(DescriptorOutline descriptor) {
		return convertBlock(descriptor.selfBlock);
	}

	private Map<String, StateClass> blocks = new HashMap<String, StateClass>();
	private Map<String, StateClass> states = new HashMap<String, StateClass>();

	private StateClass convertBlock(BlockOutline block) {
		StateClass topLevel;

		if (blocks.containsKey(block.getName())) {
			return blocks.get(block.getName());
		} else {
			// here the top level could also be the base
			topLevel = getStateFromBlockAndMethods(block, block.getDynamicMethods());
			topLevel.setIsTopLevel();
			blocks.put(block.getName(), topLevel);
		}

		StateClass baseState = getStateFromBlockAndMethods(block, block.getRequiredMethods());
		baseState.setName(block.getName());

		for (MethodOutline method : block.getRequiredMethods()) {
			addTransition(baseState, block, block.getRequiredMethods(), method);
		}

		// create the sibling states
		for (Set<MethodOutline> methods : makeCombinations(block.getDynamicMethods())) {
			StateClass combination = getStateFromBlockAndMethods(block, methods);

			if (combination != baseState) {
				combination.setBaseState(baseState);
			}

			for (MethodOutline method : methods) {
				addTransition(combination, block, methods, method);
			}
		}

		for (BlockOutline child : block.getBlocks()) {
			StateClass childState = convertBlock(child);
			childState.setParentState(topLevel);
		}

		return topLevel;
	}

	private StateClass getStateFromBlockAndMethods(BlockOutline block, Set<MethodOutline> allMethods) {
		TreeSet<String> names = new TreeSet<String>();
		for (MethodOutline method : allMethods) {
			names.add(method.getMethodKey()+"-"+method.maxOccurrences);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(block.getName());

		for (String name : names) {
			sb.append(name);
		}

		String key = sb.toString();
		if (states.containsKey(key)) {
			return states.get(key);
		}

		StateClass state = new StateClass();
		state.setName(block.getName());
		states.put(key, state);

		return state;
	}

	private void addTransition(StateClass state, BlockOutline block, Set<MethodOutline> combination, MethodOutline method) {
		Transition transition;

		if (method.isTerminal()) {
			if (method.getReturnType() != null) {
				TerminalTransition terminal = new TerminalTransition();
				terminal.setReturnType(method.getReturnType());
				transition = terminal;
			} else if (block.getReturnType() != null) {
				TerminalTransition terminal = new TerminalTransition();
				terminal.setReturnType(block.getReturnType());
				transition = terminal;
			} else {
				transition = new AscendingTransition();
			}
		} else if (method.isRequired()) {
			transition = new RecursiveTransition();
		} else {
			Set<MethodOutline> minusMethod = computeMinusMethod(combination, method);
			StateClass next = getStateFromBlockAndMethods(block, minusMethod);
			LateralTransition lateral = new LateralTransition();
			lateral.setSibling(next);
			transition = lateral;
		}

		transition.setMethodSignature(method.getMethodSignature());
		transition.setMaxOccurrences(method.isTerminal() ? -1 : method.maxOccurrences);
		transition.setMinOccurrences(method.isTerminal() ? -1 : method.minOccurrences);
		state.addTransitions(transition);

		for (BlockOutline chain : method.getBlockChain()) {
			StateClass chainClass = convertBlock(chain);
			transition.getStateChain().add(chainClass);
		}
	}

	protected Set<Set<MethodOutline>> makeCombinations(Set<MethodOutline> methods) {
		Set<Set<MethodOutline>> combinations = new HashSet<Set<MethodOutline>>();
		Stack<Set<MethodOutline>> stack = new Stack<Set<MethodOutline>>();

		// clone and push
		Set<MethodOutline> cloned = new HashSet<MethodOutline>();
		for (MethodOutline method : methods) {
			cloned.add(method.copy());
		}
		stack.push(cloned);

		while (!stack.isEmpty()) {
			Set<MethodOutline> set = stack.pop();
			combinations.add(set);

			for (MethodOutline method : set) {
				Set<MethodOutline> next = new HashSet<MethodOutline>(set);
				boolean changed = false;

				// only remove if not required
				if (!method.isRequired()) {
					next.remove(method);
					changed = true;

					// if we can afford to lose one occurrence then do it and re-add
					if (method.maxOccurrences > 1) {
						MethodOutline m = method.copy();

						m.maxOccurrences = m.maxOccurrences - 1;
						next.add(m);
					}
				}

				// only push if we've made useful changes
				if (changed) {

					// don't include empty sets here
					if (!next.isEmpty()) {
						stack.push(next);
					}
				}
			}
		}

		// always add the empty set
		combinations.add(Collections.<MethodOutline>emptySet());
		combinations = deduplicate(combinations);
		return combinations;
	}

	private Set<Set<MethodOutline>> deduplicate(Set<Set<MethodOutline>> combinations) {
		Set<Set<MethodOutline>> retval = new HashSet<Set<MethodOutline>>();
		Set<String> seen = new HashSet<String>();

		// compute the string key for the combination
		// if already seen, don't include in the result set
		for (Set<MethodOutline> combination : combinations) {
			Set<MethodOutline> sorted = new TreeSet<MethodOutline>(combination);
			StringBuilder keyBuilder = new StringBuilder();

			for (MethodOutline method : sorted) {
				keyBuilder.append(method).append("|");
			}

			String key = keyBuilder.toString();

			if (!seen.contains(key)) {
				retval.add(sorted);
				seen.add(key);
			}
		}

		return retval;
	}

	/*
		For a single invocation, computes the 'next' method, which is the minus method.
		This means that either the method is removed and then added back either with
		a decremented value or not at all. Only works for dynamic methods.
	 */
	protected Set<MethodOutline> computeMinusMethod(Set<MethodOutline> allMethods, MethodOutline method) {
		if (method.isRequired()) {
			return new HashSet<MethodOutline>(allMethods);
		}

		// compute minus method
		Set<MethodOutline> minusMethod = new HashSet<MethodOutline>(allMethods);
		minusMethod.remove(method);

		// only add back if it's not the last instance
		if (method.maxOccurrences > 1) {
			MethodOutline m = method.copy();
			m.maxOccurrences = m.maxOccurrences - 1;
			minusMethod.add(m);
		}

		return minusMethod;
	}

	// todo, might a block reference create the wrong classes?
	// or is it empty enough that it will really be the stub it's supposed to be

/*
	private void resolveBlockReferences() {
		Map<String, BlockOutline> blocks = new HashMap<String, BlockOutline>();
		_getBlockNames(outline.selfBlock, blocks);
		_resolveBlockReferences(outline.selfBlock, blocks, new IdentityHashMap<BlockOutline, Object>());
	}

	private void _getBlockNames(BlockOutline block, Map<String, BlockOutline> blocks) {
		// references aren't valid names
		if (block instanceof BlockReference) {
			return;
		}

		blocks.put(block.getName(), block);

		for (BlockOutline child : block.getBlocks()) {
			_getBlockNames(child, blocks);
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				_getBlockNames(chain, blocks);
			}
		}
	}

	private void _resolveBlockReferences(BlockOutline block, Map<String, BlockOutline> blocks, IdentityHashMap<BlockOutline, Object> seen) {
		if (seen.containsKey(block)) {
			return;
		} else {
			seen.put(block, null);
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline aBlock : method.getBlockChain()) {
				if (aBlock instanceof BlockReference) {
					BlockReference _aBlock = (BlockReference) aBlock;

					// skip if already resolved
					// we need this in case the descriptor is generated twice
					if (_aBlock.isResolved()) {
						continue;
					}

					BlockOutline actual = blocks.get(aBlock.getName());

					// couldn't find a block under that name
					if (actual == null) {
						StringBuilder sb = new StringBuilder();
						sb.append("Invalid block reference '").append(aBlock.getName()).append("'.\n")
						  .append("Referenced in method ").append(method.getMethodSignature())
						  .append(" of block '").append(block.getName()).append("'.");

						throw new DescriptorBuilderException(sb.toString());
					}

					// set the methods
					aBlock.getAllMethods().addAll(actual.getAllMethods());

					// mark resolved
					_aBlock.setResolved(true);
				}
			}
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				_resolveBlockReferences(chain, blocks, seen);
			}
		}
	}*/
}
