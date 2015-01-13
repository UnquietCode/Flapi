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

import java.util.HashMap;
import java.util.Map;

/**
 * Simple suffix generator for graph naming.
 * The output takes the form 'A'→'AC'→'FH' etc. rolling forward as needed.
 * Not so generic, but it gets the job done.
 *
 * @author Ben Fagin
 * @version 12-01-2012
 */
public class CharacterGenerator {
	private static final Map<Long, Character> BASE_26_CHARACTERS = new HashMap<Long, Character>();
	static {
		BASE_26_CHARACTERS.put(0L, 'A');
		BASE_26_CHARACTERS.put(1L, 'B');
		BASE_26_CHARACTERS.put(2L, 'C');
		BASE_26_CHARACTERS.put(3L, 'D');
		BASE_26_CHARACTERS.put(4L, 'E');
		BASE_26_CHARACTERS.put(5L, 'F');
		BASE_26_CHARACTERS.put(6L, 'G');
		BASE_26_CHARACTERS.put(7L, 'H');
		BASE_26_CHARACTERS.put(8L, 'I');
		BASE_26_CHARACTERS.put(9L, 'J');
		BASE_26_CHARACTERS.put(10L, 'K');
		BASE_26_CHARACTERS.put(11L, 'L');
		BASE_26_CHARACTERS.put(12L, 'M');
		BASE_26_CHARACTERS.put(13L, 'N');
		BASE_26_CHARACTERS.put(14L, 'O');
		BASE_26_CHARACTERS.put(15L, 'P');
		BASE_26_CHARACTERS.put(16L, 'Q');
		BASE_26_CHARACTERS.put(17L, 'R');
		BASE_26_CHARACTERS.put(18L, 'S');
		BASE_26_CHARACTERS.put(19L, 'T');
		BASE_26_CHARACTERS.put(20L, 'U');
		BASE_26_CHARACTERS.put(21L, 'V');
		BASE_26_CHARACTERS.put(22L, 'W');
		BASE_26_CHARACTERS.put(23L, 'X');
		BASE_26_CHARACTERS.put(24L, 'Y');
		BASE_26_CHARACTERS.put(25L, 'Z');
	}

	private long value = 0;
	private final String prefix;

	public CharacterGenerator() {
		this("");
	}

	public CharacterGenerator(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("prefix cannot be null");
		}

		this.prefix = prefix;
	}

	public synchronized String getAndIncrement() {
		return stringFromLong(value++);
	}

	public synchronized String get() {
		return stringFromLong(value);
	}

	private String stringFromLong(long value) {
		String retval = "";

		do {
			long part = value % 26;
			retval = BASE_26_CHARACTERS.get(part) + retval;
			value /= 26;
		} while (value != 0);

		return prefix + retval;
	}
}