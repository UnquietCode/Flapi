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

package unquietcode.tools.flapi.outline;

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.java.JavaType;
import unquietcode.tools.flapi.java.MethodSignature;
import unquietcode.tools.flapi.java.ParseException;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	private String name;
	private JavaType returnType;
	private Class<?> helperClass;
	private Class<?> beanClass;

	// nested blocks
	private final List<BlockOutline> blocks = new ArrayList<BlockOutline>();

	// constructor, used by parent to create this block
	private MethodOutline constructor;

	// block methods
	private final Set<MethodOutline> methods = new TreeSet<MethodOutline>();

	// ------------------------------ //

	public Class<?> getHelperClass() {
		return helperClass;
	}

	public void setHelperClass(Class<?> helperClass) {
		this.helperClass = helperClass;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public JavaType getReturnType() {
		return constructor != null ? constructor.getReturnType() : returnType;
	}

	public void setReturnType(JavaType returnType) {
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

		try {
			method.setMethodSignature(new MethodSignature(methodSignature));
		} catch (ParseException e) {
			throw new DescriptorBuilderException(e);
		}

		// Fun Fact: this method prompts the set to call the comparator
		// function, so the method signature must always be set first.
		methods.add(method);

		return method;
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
