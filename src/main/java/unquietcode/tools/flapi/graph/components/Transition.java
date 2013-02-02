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

package unquietcode.tools.flapi.graph.components;

import unquietcode.tools.flapi.generator.MethodImplementor;
import unquietcode.tools.flapi.graph.GenericVisitor;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.outline.MethodInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 07-10-2012
 */
public abstract class Transition implements Comparable<Transition> {
	private MethodInfo methodInfo = new MethodInfo();
	private final List<StateClass> stateChain = new ArrayList<StateClass>();
	private StateClass owner;
	private final TransitionType type;

	public abstract void accept(TransitionVisitor visitor);

	public void acceptForTraversal(GenericVisitor<StateClass> visitor) {
		for (StateClass stateClass : stateChain) {
			visitor.visit(stateClass);
		}
	}

	protected Transition(TransitionType type) {
	    this.type = type;
	}

	public TransitionType getType() {
		return type;
	}

	public List<StateClass> getStateChain() {
		return stateChain;
	}

	@Override
	public int compareTo(Transition other) {
		return methodInfo.getMethodSignature().compareTo(other.info().getMethodSignature());
	}

	void setOwner(StateClass owner) {
		this.owner = owner;
	}

	public StateClass getOwner() {
		return owner;
	}

	public String getMethodSignature() {
		return methodInfo.getMethodSignature();
	}

	public MethodInfo info() {
		return methodInfo;
	}

	public void setMethodInfo(MethodInfo methodInfo) {
		this.methodInfo = methodInfo;
	}

	public abstract Transition copy();

	protected void basicCopy(Transition copy) {
		copy.methodInfo = this.methodInfo.copy();
		copy.stateChain.addAll(this.stateChain);
	}

	public MethodImplementor methodImplementor() {
		return new MethodImplementor() {
			public boolean shouldComputeActualReturnType() {
				return !(type == TransitionType.Ascending && stateChain.isEmpty());
			}

			public boolean shouldTrackInvocations() {
				return methodInfo.getMinOccurrences() > 0;
			}

			public boolean shouldCheckInvocations() {
				return type == TransitionType.Terminal || type == TransitionType.Ascending;
			}

			public boolean shouldTransferInvocations() {
				return type == TransitionType.Lateral;
			}

			public boolean shouldCheckParentInvocations() {
				return type == TransitionType.Terminal;
			}
		};
	}


}
