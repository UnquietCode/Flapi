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

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.BlockChainHelper;
import unquietcode.tools.flapi.builder.MethodHelper;
import unquietcode.tools.flapi.outline.MethodOutline;
import unquietcode.tools.flapi.support.ObjectWrapper;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class MethodHelperImpl implements MethodHelper {
	final MethodOutline method;

	MethodHelperImpl(MethodOutline method) {
		this.method = method;
	}
	
	@Override
	public void exactly(int num) {
		between(num, num);
	}

	@Override
	public void any() {
		method.minOccurrences = 0;
		method.maxOccurrences = -1;
	}

	@Override
	public void last() {
		method.minOccurrences = 0;
		method.maxOccurrences = 1;
		method.isTerminal(true);
	}

	@Override
	public void last(Class returnType) {
		if (returnType == null) {
			throw new DescriptorBuilderException("Intermediate return type cannot be null.");
		}

		last();
		method.setReturnType(returnType);
	}

	@Override
	public void atLeast(int num) {
		if (num < 0) {
			throw new DescriptorBuilderException("must have at least >= 0");
		}

		method.maxOccurrences = -1;
		method.minOccurrences = num;
	}

	@Override
	public void atMost(int num) {
		if (num <= 0) {
			throw new DescriptorBuilderException("must have at least > 0");
		}

		method.minOccurrences = 0;
		method.maxOccurrences = num;
	}

	@Override
	public void between(int atLeast, int atMost) {
		if (atLeast < 0) {
			throw new DescriptorBuilderException("must have at least >= 0");
		}

		if (atMost <= 0) {
			throw new DescriptorBuilderException("must have at most > 0");
		}

		if (atMost < atLeast) {
			throw new DescriptorBuilderException("must have atLeast <= then atMost");
		}

		method.maxOccurrences = atLeast;
		method.minOccurrences = atMost;
	}

	@Override
	public void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(method));
	}
}
