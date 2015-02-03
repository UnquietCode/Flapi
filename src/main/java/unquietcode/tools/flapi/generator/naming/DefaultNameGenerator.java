/*********************************************************************
 Copyright 2015 the Flapi authors

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

package unquietcode.tools.flapi.generator.naming;

import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkState;

/**
 * Default name generator.
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
public class DefaultNameGenerator implements NameGenerator {
	private final AtomicInteger anonymousCounter = new AtomicInteger(1);


	@Override
	public String methodName(String methodKey) {
		return methodKey;
	}

	@Override
	public String className(String stateKey) {
		return stateKey;
	}

	@Override
	public String generatorName(String stateName) {
		return stateName+"Generator";
	}

	@Override
	public String factoryName(String stateName) {
		return stateName+"Factory";
	}

	@Override
	public String helperName(String stateName) {
		return stateName+"Helper";
	}

	@Override
	public String builderName(String stateName) {
		return stateName+"Builder";
	}

	@Override
	public String anonymousName(String methodName) {
		StringBuilder name = new StringBuilder();

		// Anon123
		name.append("Anon").append(anonymousCounter.getAndIncrement());

		// Anon123_someMethod
		if (methodName != null) {
			checkState(!methodName.trim().isEmpty(), "a valid method name is required");
			name.append("_").append(methodName);
		}

		return name.toString();
	}

	@Override
	public String wrapperName(String stateName) {
		return "Start";
	}

	@Override
	public String markerName(String stateName) {
		return "Head";
	}
}