package unquietcode.tools.flapi;

import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.BlockReference;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import javax.lang.model.SourceVersion;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ben Fagin
 * @version 08-25-2012
 *
 * Validates a descriptor to ensure that minimum requirements are met.
 * The "pre" validator works on the {@link DescriptorOutline} model.
 */
public class DescriptorPreValidator {
	private final DescriptorOutline outline;

	public DescriptorPreValidator(DescriptorOutline outline) {
		this.outline = outline;
	}

	public void validate() {
		checkThatDescriptorMethodNameIsValid();
		checkForInvalidMethodSignatures();
		checkForNameCollisions();
		checkForUnmatchedGroups();
	}

	private void checkThatDescriptorMethodNameIsValid() {
		String name = outline.getCreateMethod();

		if (!SourceVersion.isName(name)) {
			throw new DescriptorBuilderException("Invalid method name for create method: '"+name+"'.");
		}
	}

	private void checkForNameCollisions() {
		_checkForNameCollisions(outline.selfBlock, new HashSet<String>());
	}

	private void _checkForNameCollisions(BlockOutline block, Set<String> names) {
		if (block instanceof BlockReference) {
			return;
		}

		// block name collisions
		String name = block.getName();
		if (names.contains(name)) {
			throw new DescriptorBuilderException("Duplicate block name encountered: "+ name);
		} else {
			names.add(name);
		}

		// check method name collisions
		for (MethodOutline method : block.getAllMethods()) {
			String currentSignature = method.getMethodSignature();
			MethodParser currentParsed = new MethodParser(currentSignature);

			for (MethodOutline otherMethod : block.getAllMethods()) {
				if (method == otherMethod) { continue; }
				MethodParser otherParsed = new MethodParser(otherMethod.getMethodSignature());

				if (currentParsed.compilerEquivalent(otherParsed)) {
					throw new DescriptorBuilderException("Two methods with the same signature: " + currentSignature);
				}
			}
		}

		// recurse (We don't need to check child blocks since we get them from their constructors.)
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				_checkForNameCollisions(chain, names);
			}
		}
	}

	private void checkForInvalidMethodSignatures() {
		_checkForInvalidMethodSignatures(outline.selfBlock);
	}

	private void _checkForInvalidMethodSignatures(BlockOutline block) {
		if (block instanceof BlockReference) {
			return;
		}

		// check method signatures
		for (MethodOutline method : block.getAllMethods()) {
			try {
				MethodParser parsed = new MethodParser(method.getMethodSignature());
				parsed.validate();
			} catch (MethodParser.ParseException ex) {
				throw new DescriptorBuilderException(ex);
			} catch (MethodParser.ValidationException ex) {
				throw new DescriptorBuilderException(ex);
			}
		}

		// recurse
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				_checkForInvalidMethodSignatures(chain);
			}
		}
	}

	private void checkForUnmatchedGroups() {
		_checkForUnmatchedGroups(outline.selfBlock);
	}

	private void _checkForUnmatchedGroups(BlockOutline block) {
		if (block instanceof BlockReference) {
			return;
		}

		Map<Integer, AtomicInteger> counts = new HashMap<Integer, AtomicInteger>();

		// count all uses of a group
		for (MethodOutline method : block.getAllMethods()) {
			if (method.getGroup() != null && method.getGroup().equals(method.getTrigger())) {
				throw new DescriptorBuilderException(String.format(
					"Method '%s' is triggered by its own group!",
					method.getMethodSignature()
				));
			}

			tallyGroup(counts, method.getGroup());
			tallyGroup(counts, method.getTrigger());
		}

		// find counts < 2, meaning no match
		for (Map.Entry<Integer, AtomicInteger> count : counts.entrySet()) {
			if (count.getValue().get() < 2) {
				System.err.println("Block '"+block.getName()+"' has only one use of group "+count.getKey()+".");
			}
		}

		// recurse
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				_checkForUnmatchedGroups(chain);
			}
		}
	}

	private static void tallyGroup(Map<Integer, AtomicInteger> counts, Integer group) {
		if (group != null) {
			if (!counts.containsKey(group)) {
				counts.put(group, new AtomicInteger(0));
			}

			counts.get(group).incrementAndGet();
		}
	}
}
