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
	public static final int DEFAULT_NULL_INT = Integer.MIN_VALUE;

	public static final String PROJECT_VERSION; static {
		InputStream is = Constants.class.getClassLoader().getResourceAsStream("version/version.txt");
		PROJECT_VERSION = new Scanner(is).useDelimiter("\\A").next();
	}
}