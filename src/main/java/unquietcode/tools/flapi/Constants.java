/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Ben Fagin
 * @version 04-28-2012
 *
 * Y'arr, here be project constants!
 */
public final class Constants {
	private Constants() { }

	public static final String PROJECT_URL = "https://github.com/UnquietCode/Flapi";
	public static final String RETURN_TYPE_NAME = "_ReturnType";
	public static final String HELPER_VALUE_NAME = "_helper";

	public static final String PROJECT_VERSION; static {
		InputStream is = Constants.class.getClassLoader().getResourceAsStream("version/version.txt");
		PROJECT_VERSION = new Scanner(is).useDelimiter("\\A").next();
	}
}