/*******************************************************************************
 Copyright 2013 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.plugin;

import org.junit.Test;
import unquietcode.tools.flapi.plugin.test.builder.Email.EmailGenerator;
import unquietcode.tools.flapi.plugin.test.builder.Email.EmailHelper;

import java.io.File;

/**
 * @author Ben Fagin
 * @version 2013-07-03
 */
public class DescriptorTest {


	@Test
	public void usage() {
		EmailMessage message = EmailGenerator.compose(helper)
			.sender("iamthewalrus@hotmail.com")
			.addRecipient("unclebob@unquietcode.com")
			.subject("Has you seen my bucket?")
			.body("Dear sir,\nI was wondering, have you seen my bucket? It is small, metallic, somewhat used, " +
				  "and slightly smells of fish. Please let me know if you have seen, or ever do see it.\n\nThanks!")
		.send();
	}

	EmailHelper helper = new EmailHelper() {
		@Override
		public void addAttachment(File file) {
			// nothing
		}

		@Override
		public void addRecipient(String emailAddress) {
			// nothing
		}

		@Override
		public void body(String text) {
			// nothing
		}

		@Override
		public EmailMessage send() {
			return null;
		}

		@Override
		public void sender(String emailAddress) {
			// nothing
		}

		@Override
		public void subject(String subject) {
			// nothing
		}
	};
}
