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

package unquietcode.tools.flapi.examples.email;

import unquietcode.tools.flapi.examples.email.builder.Email.EmailHelper;

import java.io.File;

/**
 * @author Ben Fagin
 * @version 04-29-2012
 */
public class EmailHelperImpl implements EmailHelper, AnnotatedEmailHelper {
	private final EmailMessage email;

	public EmailHelperImpl() {
		email = new EmailMessage();
	}

	@Override
	public void subject(String subject) {
		email.setSubject(subject);
	}

	@Override
	public EmailMessage send() {
		System.out.println("Sending email...\n");
		System.out.println(email.toString());
		return email;
	}

	@Override
	public void sender(String emailAddress) {
		email.setSender(emailAddress);
	}

	@Override
	public void body(String text) {
		email.setBody(text);
	}

	@Override
	public void addCC(String emailAddress) {
		email.getCc().add(emailAddress);
	}

	@Override
	public void addRecipient(String emailAddress) {
		email.getRecipients().add(emailAddress);
	}

	@Override
	public void addBCC(String emailAddress) {
		email.getBcc().add(emailAddress);
	}

	@Override
	public void addAttachment(File file) {
		email.getAttachments().add(file);
	}
}
