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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 07-10-2012
 */
public abstract class Transition implements Comparable<Transition> {
	private final List<StateClass> stateChain = new ArrayList<StateClass>();
	private Integer minOccurrences = -1;
	private Integer maxOccurrences = -1;
	private String methodSignature;
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

	public Integer getMinOccurrences() {
		return minOccurrences;
	}

	public void setMinOccurrences(Integer minOccurrences) {
		this.minOccurrences = minOccurrences;
	}

	public Integer getMaxOccurrences() {
		return maxOccurrences;
	}

	public void setMaxOccurrences(Integer maxOccurrences) {
		this.maxOccurrences = maxOccurrences;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}

	public List<StateClass> getStateChain() {
		return stateChain;
	}

	@Override
	public int compareTo(Transition other) {
		return methodSignature.compareTo(other.methodSignature);
	}

	void setOwner(StateClass owner) {
		this.owner = owner;
	}

	public StateClass getOwner() {
		return owner;
	}

	public abstract Transition copy();
	protected void basicCopy(Transition copy) {
		copy.maxOccurrences = this.maxOccurrences;
		copy.minOccurrences = this.minOccurrences;
		copy.methodSignature = this.methodSignature;
		copy.stateChain.addAll(this.stateChain);
		copy.owner = this.owner;
	}

	// - - --  ---------  - - --   -------- - -   - ----- -- ----------  -- ---

	// ------- - - ------- -------- -  --     -- - -   ---- - -      -----------


	public MethodImplementor methodImplementor() {
		return new MethodImplementor() {

			@Override
			public boolean shouldTrackInvocations() {
				return maxOccurrences > 1;
			}

			@Override
			public boolean shouldCheckInvocations() {
				return type == TransitionType.Terminal || type == TransitionType.Ascending;
			}

			@Override
			public boolean shouldTransferInvocations() {
				return type == TransitionType.Lateral;
			}
		};
	}


}
