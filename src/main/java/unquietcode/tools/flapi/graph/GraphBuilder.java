/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph;

import unquietcode.tools.flapi.DescriptorBuilderException;
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
		findAllBlocks(blocks, descriptor);
		initializeReferenceMap(blocks, referenceMap, descriptor);

		return convertBlock(descriptor);
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
		Set<MethodOutline> triggeredMethods = block.getTriggeredMethods();
		Set<MethodOutline> allMethods = block.getAllMethods();
		allMethods.removeAll(triggeredMethods);

		if (blocks.containsKey(block.getName())) {
			return blocks.get(block.getName());
		} else if (block instanceof BlockReference) {
			BlockOutline resolved = referenceMap.get(block);
			return convertBlock(resolved);
		} else {
			topLevel = getStateFromBlockAndMethods(block, allMethods);
			topLevel.setIsTopLevel();
			blocks.put(blockName, topLevel);
		}

		// marks all states as being related to each other via
		// a shared reference to this marker object
		final Object baseIdentifier = new Object();

		// create the sibling states
		Set<StateClass> seen = Collections.newSetFromMap(new IdentityHashMap<StateClass, Boolean>());
		Set<Set<MethodOutline>> workingSet = new HashSet<Set<MethodOutline>>();
		workingSet.add(new TreeSet<MethodOutline>(allMethods));

		while (!workingSet.isEmpty()) {
			Set<Set<MethodOutline>> nextSet = new HashSet<Set<MethodOutline>>();

			for (Set<MethodOutline> combination : workingSet) {
				StateClass theState = getStateFromBlockAndMethods(block, combination);

				if (seen.contains(theState)) {
					continue;
				} else {
					seen.add(theState);
				}

				theState.setName(blockName);
				theState.setBlockMarker(baseIdentifier);

				// add dynamic methods
				for (MethodOutline method : combination) {
					Set<MethodOutline> next;
					next = addTransition(theState, block, combination, triggeredMethods, method);
					nextSet.add(next);
				}
			}

			workingSet = nextSet;
		}

		for (BlockOutline child : block.getBlocks()) {
			convertBlock(child);
		}

		return topLevel;
	}

	private StateClass getStateFromBlockAndMethods(BlockOutline block, Set<MethodOutline> allMethods) {
		TreeSet<String> names = new TreeSet<String>();
		for (MethodOutline method : allMethods) {
			names.add(method.keyString()+"-"+method.getMaxOccurrences());
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

		StateClass state = new StateClass(block.getHelperClass());
		state.setName(block.getName());
		states.put(key, state);

		return state;
	}

	private Set<MethodOutline> addTransition(
		StateClass state,
		BlockOutline block,
		Set<MethodOutline> combination,
		Set<MethodOutline> triggered,
		MethodOutline method
	){
		Transition transition;
		Set<MethodOutline> nextMethods = computeNextMethods(combination, triggered, method);
		StateClass next = getStateFromBlockAndMethods(block, nextMethods);
		boolean implicitTerminalWorthy = nextMethods.isEmpty();

		if (method.isTerminal() || implicitTerminalWorthy) {
			if (method.getReturnType() != null) {
				TerminalTransition terminal = new TerminalTransition();
				terminal.setReturnType(method.getReturnType());
				transition = terminal;
			} else if (block.getReturnType() != null) {
				TerminalTransition terminal = new TerminalTransition();
				terminal.setReturnType(block.getReturnType());
				transition = terminal;
			} else {
				transition = new AscendingTransition(!method.isRequired());
			}
		} else if (state == next) { // as in, "no changes detected"
			transition = new RecursiveTransition();
		} else {
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

		return nextMethods;
	}

	/*
		Computes the set of next methods.
			First decrements the method and removes it if dynamic (minus method).
			Then adds any triggered methods.
	 */
	private Set<MethodOutline> computeNextMethods(
		Set<MethodOutline> allMethods,
		Set<MethodOutline> triggeredMethods,
		MethodOutline method
	){
		// nothing for terminals
		if (method.isTerminal()) {
			return new TreeSet<MethodOutline>();
		}

		// compute minus method
		Set<MethodOutline> nextMethods = new TreeSet<MethodOutline>(allMethods);
		nextMethods.remove(method);

		final MethodOutline next;

		// stays removed if it's the last instance
		if (method.getMaxOccurrences() == 1) {
			next = null;
		}

		// it must be required in some way, but
		// we need to make sure it doesn't 'retrigger'
		else if (method.getMaxOccurrences() < 1) {
			MethodOutline copy = method.copy();

			nextMethods.add(copy);
			next = copy;
		}

		// only add back if it's not the last instance
		else { /* if (method.getMaxOccurrences() > 1) */
			MethodOutline m = method.copy();
			m.setMaxOccurrences(m.getMaxOccurrences() - 1);

			nextMethods.add(m);
			next = m;
		}

		// make changes based on the outgoing group number
		Integer currentGroup = method.getGroup();
		if (currentGroup != null) {

			for (MethodOutline otherMethod : new TreeSet<MethodOutline>(nextMethods)) {

				// don't remove ourselves!
				if (otherMethod == next) {
					continue;
				}

				// remove methods linked by group
				if (currentGroup.equals(otherMethod.getGroup())) {
					nextMethods.remove(otherMethod);
				}
			}

			for (MethodOutline triggeredMethod : triggeredMethods) {

				// add methods triggered by group
				if (currentGroup.equals(triggeredMethod.getTrigger())) {
					if (next != null) { next.setTriggered(); }

					// add the trigger to the next group
					if (!method.didTrigger()) {
						nextMethods.add(triggeredMethod.copy());
					}
				}
			}
		}

		return nextMethods;
	}
}