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

package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 09-05-2012
 */
public class LogMachineBuilderTest extends AbstractCompiledTest {

	@Test
	public void test() {
		generateBuilder(getTemporaryFolder());
		testCompile();
	}

	private void generateBuilder(String folder) {
		Descriptor builder = Flapi.builder()
			.setPackage("unquietcode.tools.logmachine.builder")
			.setDescriptorName("LogMachine")
			.setStartingMethodName("start")

			.addMethod("from(String location)").exactly(1)
			.addMethod("to(Enum...categories)").exactly(1)
			.addMethod("because(Throwable cause)").exactly(1)

			.addMethod("debug(String message, Object...data)").last()
			.addMethod("info(String message, Object...data)").last()
			.addMethod("trace(String message, Object...data)").last()
			.addMethod("warn(String message, Object...data)").last()
			.addMethod("error(String message, Object...data)").last()
		.build();

		builder.writeToFolder(folder);
	}
}
