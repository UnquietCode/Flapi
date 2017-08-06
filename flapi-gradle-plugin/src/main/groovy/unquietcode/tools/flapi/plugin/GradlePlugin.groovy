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

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin for generating and compiling descriptors.
 *
 * @author Ben Fagin
 * @version 2015-02-03
 */
public class GradlePlugin implements Plugin<Project> {

 	@Override
	public void apply(Project project) {
	    FlapiPluginExtension properties = project.extensions.create("flapi", FlapiPluginExtension)
	    project.apply plugin: 'java'

	    def flapiTask = project.task('flapi') << {
		    runTask(project, properties)
	    }

	    if (flapiTask == null) {
		    project.logger.info("no 'flapi' block found in build file")
		    return
	    }

	    def testClassesTask = project.tasks.getByName('testClasses')

		// Add a dependency to the 'flapi' task for all existing 'testClasses' dependencies.
	    testClassesTask.getTaskDependencies().getDependencies(testClassesTask).each {
		    flapiTask.dependsOn(it)
	    }

		// Add the 'flapi' task as a dependency of 'testClasses'.
		testClassesTask.dependsOn(flapiTask)
    }

	private void runTask(Project project, FlapiPluginExtension properties) {

	    // default for sources directory
	    if (isEmpty(properties.sourcesDirectory)) {
		    properties.sourcesDirectory = project.buildDir.getAbsolutePath()+File.separator+"generated-sources"
	    }

	    // default for classes directory
	    if (isEmpty(properties.classesDirectory)) {
			properties.classesDirectory = project.sourceSets.main.output.classesDir.getPath()
	    }

	    // set up shared plugin helper
	    final PluginHelper helper = new PluginHelper(properties.classesDirectory, properties.sourcesDirectory) {
		    protected @Override Exception handleError(String message, Throwable cause) throws Exception {
			    throw new RuntimeException(message, cause);
		    }

		    protected @Override Exception handleFailure(String message, Throwable cause) throws Exception {
			    throw new Exception(message, cause);
		    }

		    protected @Override void logInfo(String message) {
			    project.logger.info(message)
		    }

		    protected @Override void logWarn(String message) {
			    project.logger.warn(message)
		    }

		    protected @Override void logError(String message) {
			    project.logger.error(message)
		    }

		    protected @Override URLClassLoader getCompiledClassloader() throws Exception {
			    List<URL> urls = new ArrayList<>();

			    // dependencies
				for (def path : project.configurations['testCompile']) {
				    urls.add(path.toURI().toURL())
				}

			    // sources
			    for (def sourceSet : project.sourceSets) {
			        urls.add(sourceSet.output.classesDir.toURI().toURL())
			    }

			    return new URLClassLoader(urls.toArray(new URL[urls.size()]), getClass().getClassLoader());
		    }
	    };

	    // configure it
	    helper.setIncludeRuntime(properties.includeRuntime)
	    helper.setWriteClasses(properties.writeClasses)
	    helper.setWriteSources(properties.writeSources)

	    // run it
		if (properties.descriptorClasses != null && properties.descriptorClasses.length > 0) {
			project.logger.warn("the 'descriptorClasses' property is deprecated, use 'descriptors' instead")
			helper.processDescriptors(properties.descriptorClasses)
		} else {
			Object[] descriptors = properties.descriptors ?: []
			helper.processDescriptors(descriptors)
		}
	}

	private static boolean isEmpty(String s) {
		return s == null || s.trim().isEmpty()
	}
}