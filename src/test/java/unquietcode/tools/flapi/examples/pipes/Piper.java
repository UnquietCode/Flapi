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

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ralph Bensmann
 * @version 09 OCTOBER 2010
 *
 * From the 'Art of Coding' blog
 * http://blog.art-of-coding.eu/piping-between-processes/
 */
public class Piper implements java.lang.Runnable {

	private InputStream input;

	private OutputStream output;

	public Piper(InputStream input, OutputStream output) {
		this.input = input;
		this.output = output;
	}

	public void run() {
		try {
			// Create 512 bytes buffer
			byte[] b = new byte[512];
			int read = 1;
			// As long as data is read; -1 means EOF
			while (read > -1) {
				// Read bytes into buffer
				read = input.read(b, 0, b.length);
				//System.out.println("read: " + new String(b));
				if (read > -1) {
					// Write bytes to output
					output.write(b, 0, read);
				}
			}
		} catch (Exception e) {
			// Something happened while reading or writing streams; pipe is broken
			throw new RuntimeException("Broken pipe", e);
		} finally {
			try {
				input.close();
			} catch (Exception e) {
			}
			try {
				output.close();
			} catch (Exception e) {
			}
		}
	}

	public static InputStream pipe(Process...proc) throws java.lang.InterruptedException {
		// Start Piper between all processes
		Process p1;
		Process p2;
		for (int i = 0; i < proc.length; i++) {
			p1 = proc[i];
			// If there's one more process
			if (i + 1 < proc.length) {
				p2 = proc[i + 1];
				// Start piper
				new Thread(new Piper(p1.getInputStream(), p2.getOutputStream())).start();
			}
		}

		Process last = proc[proc.length - 1];
		// Wait for last process in chain; may throw InterruptedException
		last.waitFor();
		// Return its InputStream
		return last.getInputStream();
	}

}