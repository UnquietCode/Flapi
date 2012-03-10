package unquietcode.tools.flapi.outline;

import unquietcode.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-09-2012
 */
public class BlockOutline {
	List<PathSegment> path;


	String name;



	InterfaceOutline iHelper = new InterfaceOutline();	
	ClassOutline cHelper;
	InterfaceOutline iTopLevel;
	ClassOutline cTopLevel;
	InterfaceOutline iBase;
	ClassOutline cBase;

	Set<MethodOutline> methods = new HashSet<MethodOutline>();
	Set<InterfaceOutline> interfaces = new HashSet<InterfaceOutline>();
	Set<MethodOutline> dynamicMethods;
	Set<MethodOutline> requiredMethods;

	
	class PathSegment {
		Pair<InterfaceOutline, InterfaceOutline> path;
		MethodOutline method;
	}

}
