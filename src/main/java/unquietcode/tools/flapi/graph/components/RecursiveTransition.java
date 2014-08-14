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

package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.runtime.TransitionType;

/**
 * @author Ben Fagin
 * @version 08-15-2012
 */
public class RecursiveTransition extends Transition {
	public RecursiveTransition() {
		super(TransitionType.Recursive);
	}


	/**
	 * Alias for {@link #getOwner()}.
	 * @return the {@link StateClass} for this transition
	 */
	public StateClass getSelf() {
		return getOwner();
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		RecursiveTransition copy = new RecursiveTransition();
		basicCopy(copy);
		return copy;
	}
}
