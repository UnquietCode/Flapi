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

package unquietcode.tools.flapi.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a static method which returns a {@link Descriptor} object,
 * generate the classes and write them out to the specified output
 * directory.
 *
 * @author Ben Fagin
 * @version 2013-07-03
 */
@Mojo(
	name="generate",
	defaultPhase=LifecyclePhase.PROCESS_TEST_CLASSES,
	requiresDependencyResolution=ResolutionScope.TEST
)
public class FlapiBuildPlugin extends AbstractMojo {

	@Parameter(defaultValue="${project}", required=true, readonly=true)
	private MavenProject project;

	/**
	 * The comma separated list of {@link DescriptorMaker} classes.
	 */
	@Parameter(required=false)
	private String descriptorClasses;

	/**
	 * The directory to which the generated classes
	 * will be written.
	 */
	@Parameter(defaultValue="${project.build.outputDirectory}")
	private String classesDirectory;

	/**
	 * The directory to which the generated sources
	 * will be written.
	 */
	@Parameter(defaultValue="${project.build.directory}/generated-sources")
	private String sourcesDirectory;

	/**
	 * If true, the runtime classes will be written
	 * out alongside the generated classes.
	 */
	@Parameter(defaultValue="true")
	private boolean includeRuntime;

	/**
	 * If true, the sources will be written.
	 */
	@Parameter(defaultValue="true")
	private boolean writeSources;

	/**
	 * If true, the sources will be compiled and written.
	 */
	@Parameter(defaultValue="true")
	private boolean writeClasses;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (descriptorClasses == null) {
			descriptorClasses = "";
		}

		// set up shared plugin helper
		final PluginHelper helper = new PluginHelper(classesDirectory, sourcesDirectory) {
			protected @Override Exception handleError(String message, Throwable cause) throws Exception {
				throw new MojoExecutionException(message, cause);
			}

			protected @Override Exception handleFailure(String message, Throwable cause) throws Exception {
				throw new MojoFailureException(message, cause);
			}

			protected @Override void logInfo(String message) {
				getLog().info(message);
			}

			protected @Override void logWarn(String message) {
				getLog().warn(message);
			}

			protected @Override void logError(String message) {
				getLog().error(message);
			}

			protected @Override URLClassLoader getCompiledClassloader() throws Exception {
				List<URL> urls = new ArrayList<>();

				for (Object object : project.getTestClasspathElements()) {
					String path = (String) object;
					urls.add(new File(path).toURI().toURL());
				}

				return new URLClassLoader(urls.toArray(new URL[urls.size()]), getClass().getClassLoader());
			}
		};

		// configure it
		helper.setIncludeRuntime(includeRuntime);
		helper.setWriteClasses(writeClasses);
		helper.setWriteSources(writeSources);

		// run it
		try {
			helper.processDescriptors(descriptorClasses.split(","));
		} catch (Exception e) {
			throw new MojoExecutionException("error while running plugin", e);
		}
	}
}