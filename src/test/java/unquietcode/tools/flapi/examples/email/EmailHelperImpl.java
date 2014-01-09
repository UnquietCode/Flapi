/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.email;

import unquietcode.tools.flapi.examples.email.builder.Email.EmailHelper;

import java.io.File;

/**
 * @author Ben Fagin
 * @version 04-29-2012
 */
public class EmailHelperImpl implements EmailHelper {
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
