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

package unquietcode.tools.flapi.plugin

/**
 * Configuration closure for the Flapi Gradle plugin.
 *
 * @author Ben Fagin
 * @version 2015-03-17
 */
public class FlapiPluginExtension {

	/**
	 * The list of {@link unquietcode.tools.flapi.DescriptorMaker} classes.
	 *
	 * @deprecated use the 'descriptors' property instead
	 */
	@Deprecated
	String[] descriptorClasses;

	/**
	 * The list of descriptors to generate. These can be any one of the following:
	 *
	 *  + {@link unquietcode.tools.flapi.DescriptorMaker} Class FQCN string
	 *  + {@link unquietcode.tools.flapi.DescriptorMaker} Class
	 *  + {@link unquietcode.tools.flapi.DescriptorMaker} instance
	 *  + {@link unquietcode.tools.flapi.Descriptor} instance
	 */
	Object[] descriptors;

	/**
	 * The directory to which the generated classes
	 * will be written.
	 */
	String classesDirectory;

	/**
	 * The directory to which the generated sources
	 * will be written.
	 */
	String sourcesDirectory;

	/**
	 * If true, the runtime classes will be written
	 * out alongside the generated classes.
	 */
	boolean includeRuntime = true

	/**
	 * If true, the sources will be written.
	 */
	boolean writeSources = true

	/**
	 * If true, the sources will be compiled and written.
	 */
	boolean writeClasses = true
}