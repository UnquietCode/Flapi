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

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.graph.components.*;
import unquietcode.tools.flapi.outline.*;

import java.util.*;


/**
 * @author Ben Fagin
 * @version 08-12-2012
 */
public class GraphBuilder {
	private Map<String, StateClass> blocks = new HashMap<String, StateClass>();
	private Map<String, StateClass> states = new HashMap<String, StateClass>();
	private Map<BlockReference, BlockOutline> referenceMap = new IdentityHashMap<BlockReference, BlockOutline>();


	public StateClass buildGraph(DescriptorOutline descriptor) {
		// resolve block references
		Map<String, BlockOutline> blocks = new HashMap<String, BlockOutline>();
		findAllBlocks(blocks, descriptor.selfBlock);
		initializeReferenceMap(blocks, referenceMap, descriptor.selfBlock);

		return convertBlock(descriptor.selfBlock);
	}

	private static void findAllBlocks(Map<String, BlockOutline> blocks, BlockOutline block) {
		if (block instanceof BlockReference) {
			return;
		}

		blocks.put(block.getName(), block);

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				findAllBlocks(blocks, chain);
			}
		}

		for (BlockOutline child : block.getBlocks()) {
			findAllBlocks(blocks, child);
		}
	}

	private static void initializeReferenceMap(Map<String, BlockOutline> blocks,
		                                       Map<BlockReference, BlockOutline> references,
	                                           BlockOutline block
	){
		if (block instanceof BlockReference) {
			BlockOutline resolved = blocks.get(block.getName());
			if (resolved == null) {
				throw new DescriptorBuilderException("Could not resolve block reference with name '"+block.getName()+"'.");
			}
			references.put((BlockReference) block, resolved);
		}

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				initializeReferenceMap(blocks, references, chain);
			}
		}
	}

	private StateClass convertBlock(BlockOutline block) {
		StateClass topLevel;
		String blockName = block.getName();

		if (blocks.containsKey(block.getName())) {
			return blocks.get(block.getName());
		} else if (block instanceof BlockReference) {
			BlockOutline resolved = referenceMap.get(block);
			return convertBlock(resolved);
		} else {
			topLevel = getStateFromBlockAndMethods(block, block.getDynamicMethods());
			topLevel.setIsTopLevel();
			blocks.put(blockName, topLevel);
		}

		Set<MethodOutline> blockRequiredMethods = block.getRequiredMethods();
		StateClass baseState = getStateFromBlockAndMethods(block, blockRequiredMethods);
		baseState.setName(blockName);

		for (MethodOutline requiredMethod : blockRequiredMethods) {
			addTransition(baseState, block, blockRequiredMethods, requiredMethod);
		}

		// create the sibling states
		for (Set<MethodOutline> combination : makeCombinations(block.getDynamicMethods())) {
			StateClass theState = getStateFromBlockAndMethods(block, combination);

			if (theState != baseState) {
				theState.setBaseState(baseState);
			}

			for (MethodOutline dynamicMethod : combination) {
				addTransition(theState, block, combination, dynamicMethod);
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
			String key = AbstractGenerator.makeMethodKey(method.getMethodSignature());
			names.add(key+"-"+method.getMaxOccurrences());
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

	private void addTransition(
		StateClass state,
		BlockOutline block,
		Set<MethodOutline> combination,
		MethodOutline method
	){
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

		transition.setMethodInfo(((MethodInfo) method).copy());
		state.addTransitions(transition);

		// state chain
		for (BlockOutline chain : method.getBlockChain()) {
			StateClass chainClass = convertBlock(chain);
			transition.getStateChain().add(chainClass);
		}
	}

	/**
	 * Returns the set of all possible combinations of methods which
	 * result from permuting the provided set of methods.
	 */
	protected static Set<Set<MethodOutline>> makeCombinations(Set<MethodOutline> methods) {
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
					if (method.getMaxOccurrences() > 1) {
						MethodOutline m = method.copy();

						m.setMaxOccurrences(m.getMaxOccurrences() - 1);
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

	private static Set<Set<MethodOutline>> deduplicate(Set<Set<MethodOutline>> combinations) {
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
		if (method.getMaxOccurrences() > 1) {
			MethodOutline m = method.copy();
			m.setMaxOccurrences(m.getMaxOccurrences() - 1);
			minusMethod.add(m);

		// otherwise it stays removed, but also remove members of the same group
		} else {
			Integer currentGroup = method.getGroup();

			if (currentGroup != null) {
				for (MethodOutline otherMethod : new HashSet<MethodOutline>(minusMethod)) {
					if (currentGroup.equals(otherMethod.getGroup())) {
						minusMethod.remove(otherMethod);
					}
				}
			}
		}

		return minusMethod;
	}
}
