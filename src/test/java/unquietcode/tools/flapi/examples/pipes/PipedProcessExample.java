/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.pipes;

import org.junit.Ignore;
import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessBuilder;
import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessGenerator;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ben Fagin
 * @version 02-05-2013
 */
public class PipedProcessExample implements DescriptorMaker {

	@Override
	public Descriptor descriptor() {
		return Flapi.builder()
			.setDescriptorName("Process")
			.setPackage("unquietcode.tools.flapi.examples.pipes.builder")
			.setStartingMethodName("begin")

			.addMethod("withProcess(String name)").exactly(1)
			.addMethod("addArgument(String argument)").any()
			.addMethod("addArgument(String name, String value)").any()

			.addBlockReference("Process", "pipe()").last()
			.addMethod("run()").last(InputStream.class)
		.build();
	}

	/*
		Example which shows usage with the raw Java classes.
	 */
	@Test
	@Ignore("this isn't platform independent")
	public void test() throws Exception {
		Process p1 = Runtime.getRuntime().exec(new String[]{"echo", "hello world"});

		byte[] _p1Out = IOUtils.readFully(p1.getInputStream(), -1, true);
		String p1Out = new String(_p1Out);
		System.out.println(p1Out);

		Process p2 = Runtime.getRuntime().exec(new String[]{"cut", "-c", "1-5"});
		p2.getOutputStream().write(_p1Out);
		p2.getOutputStream().close();

		byte[] _p2Out = IOUtils.readFully(p2.getInputStream(), -1, true);
		String p2Out = new String(_p2Out);
		System.out.println(p2Out);
	}

	@Test
	@Ignore("this isn't platform independent")
	public void usage() {
		InputStream is = newProcess()
			.withProcess("echo")
			.addArgument("hello world")

			.pipe()
				.withProcess("cut")
				.addArgument("-c", "1-5")
		.run();

		while (true) {
			try {
				byte[] bytes = IOUtils.readFully(is, 256, false);
				if (bytes.length == 0) { break; }
				System.out.print(new String(bytes));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	ProcessBuilder.$ newProcess() {
		return ProcessGenerator.begin(new ProcessHelperImpl());
	}
}