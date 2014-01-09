/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import unquietcode.tools.flapi.examples.GenerateExamples;

/**
 * @author Ben Fagin
 * @version 2013-07-01
 */
public final class GenerateEverything {

	/*
		Regenerates the examples.
	 */
	public static void main(String[] args) {
		String[] mainPath = new String[]{args[0]};
		String[] examplePath = new String[]{args[1]};

		GenerateExamples.main(examplePath);
	}
}
