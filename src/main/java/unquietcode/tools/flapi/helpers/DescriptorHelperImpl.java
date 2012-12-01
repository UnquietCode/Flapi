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

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.BlockHelper;
import unquietcode.tools.flapi.builder.DescriptorHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class DescriptorHelperImpl implements DescriptorHelper {
	final DescriptorOutline outline = new DescriptorOutline();

	@Override
	public void setDescriptorName(String descriptorName) {
		if (descriptorName == null || descriptorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}

		outline.setDescriptorName(descriptorName);
	}

	@Override
	public void setReturnType(Class returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}

		outline.selfBlock.setReturnType(returnType);
	}

	@Override
	public void setStartingMethodName(String methodName) {
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}

		outline.setCreateMethod(methodName);
	}

	@Override
	public void enableCondensedClassNames() {
		outline.enableCondensedNames(true);
	}

	@Override
	public void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		BlockHelperImpl._addBlockReference(outline.selfBlock, blockName, methodSignature, _helper1);
	}

	@Override
	public Descriptor build() {
		outline.prepare();
		return new Descriptor(outline);
	}

	@Override
	public void setPackage(String packageName) {
		outline.setPackageName(packageName);
	}

	@Override
	public void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1) {
		BlockHelperImpl._addMethod(outline.selfBlock, methodSignature, _helper1);
	}

	@Override
	public void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline.selfBlock, blockName, methodSignature, _helper1, _helper2);
	}

	@Override
	public void startBlock(String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2) {
		BlockHelperImpl._startBlock(outline.selfBlock, null, methodSignature, _helper1, _helper2);
	}
}
