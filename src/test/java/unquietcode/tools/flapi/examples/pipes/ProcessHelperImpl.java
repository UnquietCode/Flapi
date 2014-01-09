/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.pipes;

import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 02-05-2013
 */
public class ProcessHelperImpl implements ProcessHelper {
	private String processName;
	private final List<String> arguments = new ArrayList<String>();
	private final List<ProcessHelperImpl> processChain;


	public ProcessHelperImpl() {
		this(new ArrayList<ProcessHelperImpl>());
	}

	protected ProcessHelperImpl(List<ProcessHelperImpl> chain) {
		this.processChain = chain;
		processChain.add(this);
	}

	@Override
	public void withProcess(String name) {
		processName = name;
	}

	@Override
	public void addArgument(String argument) {
		arguments.add(argument);
	}

	@Override
	public void addArgument(String name, String value) {
		arguments.add(name);
		arguments.add(value);
	}

	@Override
	public void pipe(AtomicReference<ProcessHelper> _helper1) {
		_helper1.set(new ProcessHelperImpl(processChain));
	}

	@Override
	public InputStream run() {
		List<Process> chain = new ArrayList<Process>();

		for (ProcessHelperImpl helper : processChain) {
			List<String> commandString = new ArrayList<String>();
			commandString.add(helper.processName);
			commandString.addAll(helper.arguments);
			String[] cmd = commandString.toArray(new String[commandString.size()]);

			try {
				Process p = Runtime.getRuntime().exec(cmd);
				chain.add(p);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		Process processes[] = chain.toArray(new Process[chain.size()]);

		if (processes.length == 1) {
			return processes[0].getInputStream();
		} else {
			try {
				return Piper.pipe(processes);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
