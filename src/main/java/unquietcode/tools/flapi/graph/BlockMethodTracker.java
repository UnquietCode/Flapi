package unquietcode.tools.flapi.graph;

import unquietcode.tools.flapi.graph.components.Transition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author Ben Fagin
* @version 2013-06-29
*/
public class BlockMethodTracker {
	private final Map<String, Set<String>> seenMethods = new HashMap<String, Set<String>>();
	private final boolean useMethodKey;

	public BlockMethodTracker() {
		this(false);
	}

	public BlockMethodTracker(boolean useMethodKey) {
		this.useMethodKey = useMethodKey;
	}

	public boolean seen(Transition transition) {
		String name = transition.getOwner().getName();
		String key = transition.getMethodSignature();
		if (useMethodKey) { key = transition.info().keyString(); }

		Set<String> methods = seenMethods.get(name);

		if (methods == null) {
			methods = new HashSet<String>();
			seenMethods.put(name, methods);
		}

		if (methods.contains(key)) {
			return true;
		} else {
			methods.add(key);
			return false;
		}
	}
}
