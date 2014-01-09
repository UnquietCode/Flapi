/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

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
