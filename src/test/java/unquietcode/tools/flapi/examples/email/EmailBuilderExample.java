package unquietcode.tools.flapi.examples.email;

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorHelperImpl;
import unquietcode.tools.flapi.builder.DescriptorGenerator;
import unquietcode.tools.flapi.examples.email.builder.EmailGenerator;

/**
 * @author Benjamin Fagin
 * @version 04-27-2012
 *
 * A test which builds an 'email builder' descriptor.
 */
public class EmailBuilderExample {

	@Test
	public void descriptorGenerator() {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.examples.email.builder")
				.setStartingMethodName("compose")
				.setDescriptorName("Email")
				.setReturnType(EmailMessage.class)

				.addMethod("setSubject(String subject)").once()
				.addMethod("addRecipient(String emailAddress)").atLeast(1)
				.addMethod("setSender(String emailAddress)").atLeast(1)
				.addMethod("addCC(String emailAddress)").any()
				.addMethod("addBCC(String emailAddress)").any()
				.addMethod("setBody(String text)").once()
				.addMethod("addAttachment(java.io.File file)").any()
				.addMethod("send()").last()
			.build()
		;
	}

	@Test
	public void usage() {
		EmailMessage message = EmailGenerator.compose(new EmailHelperImpl())
			.setSender("iamthewalrus@hotmail.com")
			.addRecipient("unclebob@unquietcode.com")
			.setSubject("Has you seen my bucket?")
			.setBody("Dear sir,\nI was wondering, have you seen my bucket? It is small, metallic, somewhat used, " +
					 "and slightly smells of fish. Please let me know if you have or do ever see it.\n\nThanks!")
		.send();
	}

}