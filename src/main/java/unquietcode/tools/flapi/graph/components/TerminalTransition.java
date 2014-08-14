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
public class TerminalTransition extends Transition {
	String returnType;

	public TerminalTransition() {
		super(TransitionType.Terminal);
	}

	public TerminalTransition(String returnType) {
		this();
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public void accept(TransitionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Transition copy() {
		TerminalTransition copy = new TerminalTransition();
		basicCopy(copy);
		copy.returnType = this.returnType;
		return copy;
	}
}
