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

import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.runtime.TransitionType;

/**
 * Lateral transitions represent a change from one state
 * to the same state minus some method.
 *
 * @author Ben Fagin
 * @version 08-15-2012
 */
public class LateralTransition extends Transition {
	StateClass sibling;

	public LateralTransition() {
		super(TransitionType.Lateral);
	}

	public StateClass getSibling() {
		return sibling;
	}

	public void setSibling(StateClass sibling) {
		this.sibling = sibling;
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		LateralTransition copy = new LateralTransition();
		basicCopy(copy);
		copy.sibling = this.sibling;
		return copy;
	}

	@Override
	public void acceptForTraversal(GenericVisitor<StateClass> visitor) {
		super.acceptForTraversal(visitor);
		visitor.visit(sibling);
	}
}


























/*
	Could I generate a state machine, which would be the
	'keeper of information', 'keeper of state', 'state aware'
	something which lets it govern the process, oversee it.

	What if the interface directly accessed the state machine. How would
	users respond to it?


	XHTML -> Element_setValue

	We would have to call the method, just as we do in the helper.



 */
