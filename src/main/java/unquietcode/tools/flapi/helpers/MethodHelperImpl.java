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
import unquietcode.tools.flapi.builder.BlockChain.BlockChainHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.concurrent.atomic.AtomicReference;

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
		method.setMinOccurrences(0);
		method.setMaxOccurrences(-1);
	}

	@Override
	public void last() {
		method.setMinOccurrences(0);
		method.setMaxOccurrences(1);
		method.isTerminal(true);
	}

	@Override
	public void last(Class returnType) {
		if (returnType == null) {
			throw new DescriptorBuilderException("Intermediate return type cannot be null.");
		}

		last(returnType.getName());
	}

	@Override
	public void last(String returnType) {
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

		method.setMaxOccurrences(-1);
		method.setMinOccurrences(num);
	}

	@Override
	public void atMost(int num, int group) {
		atMost(num);
		method.setGroup(group);
	}

	@Override
	public void atMost(int num) {
		if (num <= 0) {
			throw new DescriptorBuilderException("must have at least > 0");
		}

		method.setMinOccurrences(0);
		method.setMaxOccurrences(num);
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

		method.setMaxOccurrences(atLeast);
		method.setMinOccurrences(atMost);
	}

	@Override
	public void addBlockChain(AtomicReference<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(method));
	}

	@Override
	public void withDocumentation(AtomicReference<DocumentationHelper> _helper1) {
		DocumentationHelper helper = new DocumentationHelperImpl(method);
		_helper1.set(helper);
	}

	@Override
	public void withDocumentation(String documentation) {
		method.setDocumentation(documentation);
	}

	@Override
	public void markAsDeprecated(String reason) {
		method.setDeprecated(reason);
	}

	@Override
	public void after(int group) {
		method.setTrigger(group);
	}
}
