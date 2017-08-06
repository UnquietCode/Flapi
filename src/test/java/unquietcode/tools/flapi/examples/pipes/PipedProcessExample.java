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

		String result = readStream(is);
		System.out.println(result);
	}

	private static String readStream(InputStream stream) {
		StringBuilder string = new StringBuilder();

		while (true) {
			try {
				byte[] bytes = IOUtils.readFully(stream, 256, false);
				if (bytes.length == 0) { break; }
				string.append(new String(bytes));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}

		return string.toString();
	}

	ProcessBuilder.Start newProcess() {
		return ProcessGenerator.begin(new ProcessHelperImpl());
	}
}