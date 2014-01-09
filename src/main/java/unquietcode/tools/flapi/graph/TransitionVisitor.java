/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.graph;

import unquietcode.tools.flapi.graph.components.*;

/**
 * @author Ben Fagin
 * @version 08-16-2012
 */
public interface TransitionVisitor {
	void visit(Transition transition);
	void visit(AscendingTransition transition);
	void visit(LateralTransition transition);
	void visit(RecursiveTransition transition);
	void visit(TerminalTransition transition);

	/**
	 * Abstract base class which can be used to selectively implement
	 * the {@link unquietcode.tools.flapi.graph.TransitionVisitor} interface methods.
	 */
	public abstract static class $ implements TransitionVisitor {
		public @Override void visit(Transition transition) {
			// nothing
		}

		public @Override void visit(AscendingTransition transition) {
			// nothing
		}

		public @Override void visit(LateralTransition transition) {
			// nothing
		}

		public @Override void visit(RecursiveTransition transition) {
			// nothing
		}

		public @Override void visit(TerminalTransition transition) {
			// nothing
		}
	}
}
