package unquietcode.tools.flapi;


import org.junit.Test;
import unquietcode.tools.flapi.builder.*;

/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public class ModifiedLogMachineTest {

	@Test
	public void LogMachineTest() {
		//DescriptorBuilder builder =

//		DescriptorGenerator.create("LogMachine")
//			.setPackage("unquietcode.tools.logmachine.builder")
//
//
//				.addMethod("debug(String message, Object...data)").only()
//				.addMethod("warn(String message, Object...data)").only()
//				.addMethod("info(String message, Object...data)").only()
//				.addMethod("error(String message, Object...data)").only()
//				.addMethod("trace(String message, Object...data)").only()
//
//				.addMethod("from(String location)").once()
//				.addMethod("to(Enum...categories)").once()
//				.addMethod("because(Throwable cause)").once()
//
//				.startBlock("Configuration", "configure()").once()
//					.addMethod("setTitle(String title)").once()
//					.addMethod("showTimestamps(boolean value)").once()
//				.endBlock()
//			.endBlock()
//		;
		
		//builder.build();
	}
}

/*
	each top level block is its own thing, returning a 'builder'
	each inner block returns the parent


	Here's what this needs to look like:

		DescriptorGenerator.create("LogMachine")
			.setPackage("unquietcode.tools.logmachine.builder")

			.addMethod("debug(String message, Object...data)").only()
			.addMethod("warn(String message, Object...data)").only()
			.addMethod("info(String message, Object...data)").only()
			.addMethod("error(String message, Object...data)").only()
			.addMethod("trace(String message, Object...data)").only()

			.addMethod("from(String location)").once()
			.addMethod("to(Enum...categories)").once()
			.addMethod("because(Throwable cause)").once()

			.startBlock("Configuration", "configure()").once()
				.addMethod("setTitle(String title)").once()
				.addMethod("showTimestamps(boolean value)").once()
			.endBlock()

			.build()
		;
*/