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
