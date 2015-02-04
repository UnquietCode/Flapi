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

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Descriptor.DescriptorHelper;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.java.JavaType;
import unquietcode.tools.flapi.java.ParseException;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class DescriptorHelperImpl extends DescriptorConfiguratorHelperImpl implements DescriptorHelper {

	public DescriptorHelperImpl() {
		super(new DescriptorOutline());
	}

	@Override
	public void setDescriptorName(String descriptorName) {
		if (descriptorName == null || descriptorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		outline.setName(descriptorName);
	}

	@Override
	public void setReturnType(Class returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}
		outline.setReturnType(JavaType.from(returnType));
	}

	@Override
	public void setReturnType(String returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}

		final JavaType type;
		try {
			type = JavaType.from(returnType);
		} catch (ParseException e) {
			throw new DescriptorBuilderException("invalid return type '"+returnType+"'");
		}

		outline.setReturnType(type);
	}

	@Override
	public void addBlockReference(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		BlockHelperImpl._addBlockReference(outline, blockName, methodSignature, _helper1);
	}

	@Override
	public void addMethod(String methodSignature, AtomicReference<MethodHelper> _helper1) {
		BlockHelperImpl._addMethod(outline, methodSignature, _helper1);
	}

	@Override
	public void startBlock(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline, blockName, methodSignature, _helper1, _helper2);
	}

	@Override
	public void startBlock(String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline, null, methodSignature, _helper1, _helper2);
	}

	@Override
	public void addEnumSelector(Class clazz, String methodSignature, AtomicReference<MethodHelper> _helper1) {
		BlockHelperImpl._addEnumSelector(outline, clazz, methodSignature, _helper1);
	}

	@Override
	public void addMixin(Class helper) {
		BlockHelperImpl._addMixin(outline, helper);
	}

	@Override
	public void addMixin(String blockName) {
		BlockHelperImpl._addMixin(outline, blockName);
	}
}