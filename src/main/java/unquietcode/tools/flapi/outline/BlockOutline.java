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

package unquietcode.tools.flapi.outline;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	private String name;
	private String returnType = null;

	// nested blocks
	private final List<BlockOutline> blocks = new ArrayList<BlockOutline>();

	// constructor, used by parent to create this block
	private MethodOutline constructor;

	// block methods
	private final Set<MethodOutline> methods = new HashSet<MethodOutline>();

	// ------------------------------ //

	@Deprecated
	public BlockOutline(BlockOutline parentBlock) {
		this();
	}

	public BlockOutline() { }

	public String getReturnType() {
		return constructor != null ? constructor.getReturnType() : returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<BlockOutline> getBlocks() {
		return Collections.unmodifiableList(blocks);
	}

	public MethodOutline getConstructor() {
		return constructor;
	}

	public void setConstructor(MethodOutline constructor) {
		this.constructor = constructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public BlockOutline addBlock(String blockName) {
		BlockOutline block = new BlockOutline();
		block.name = blockName;
		blocks.add(block);

		return block;
	}

	public MethodOutline addMethod(String methodSignature) {
		MethodOutline method = new MethodOutline();
		method.setMethodSignature(methodSignature);
		methods.add(method);

		return method;
	}

	public Set<MethodOutline> getRequiredMethods() {
		Set<MethodOutline> required = new TreeSet<MethodOutline>();
		
		for (MethodOutline method : methods) {
			if (method.isRequired()) {
				required.add(method);
			}
		}
		
		return required;
	}

	public Set<MethodOutline> getDynamicMethods() {
		Set<MethodOutline> dynamic = new TreeSet<MethodOutline>();

		for (MethodOutline method : methods) {
			if (!method.isRequired() && method.getTrigger() == null) {
				dynamic.add(method);
			}
		}

		return dynamic;
	}

	public Set<MethodOutline> getTriggeredMethods() {
		Set<MethodOutline> triggered = new TreeSet<MethodOutline>();

		for (MethodOutline method : methods) {
			if (!method.isRequired() && method.getTrigger() != null) {
				triggered.add(method);
			}
		}

		return triggered;
	}

	public Set<MethodOutline> getAllMethods() {
		return methods;
	}
}
