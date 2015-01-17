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

/**
 * Interface which defines a contract for managing the
 * generated names of the various state classes.
 *
 * TODO delete after propagation
 *
 * @author Ben Fagin
 * @version 2015-01-14
 */
public interface NameGenerator {

	/**
	 * Using the provided method name, return a
	 * suitable string to use as a replacement.
	 *
	 * This name is incorporated into the complete class
	 * name, which can then be further altered.
	 *
	 * @param methodKey the unique method key
	 * @return a new method name, or the same, but not null
	 */
	String methodName(String methodKey);

	/**
	 * Using the provided state name, return a
	 * suitable string to use as a replacement.
	 *
	 * @param stateKey the unique state key
	 * @return a new state name, or the same, but not null
	 */
	String className(String stateKey);
}