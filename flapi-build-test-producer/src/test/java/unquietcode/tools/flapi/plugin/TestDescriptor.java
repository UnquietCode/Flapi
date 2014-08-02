package unquietcode.tools.flapi.plugin;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;

/**
 * @author Benjamin Fagin
 * @version 04-27-2012
 *
 * A test which builds an 'email builder' descriptor.
 */
public class TestDescriptor implements DescriptorMaker {

	public Descriptor descriptor() {
		Descriptor builder = Flapi.builder()
			.setPackage("unquietcode.tools.flapi.plugin.test.builder")
			.setStartingMethodName("compose")
			.setDescriptorName("Email")

			.addMethod("subject(String subject)").atMost(1)
			.addMethod("addRecipient(String emailAddress)").atLeast(1)
			.addMethod("sender(String emailAddress)").exactly(1)
			.addMethod("body(String text)").atMost(1)
			.addMethod("addAttachment(java.io.File file)").any()
			.addMethod("send()").last(EmailMessage.class)

			.startBlock("ABlock", "block()")
                .addAnnotation(TestAnnotation.class)
                    .withParameter("someValue", "a")
                    .withParameter("someValues", new String[]{"a", "b"})
                .finish()
            .any()
				.addEnumSelector(TestEnum.class, "test()").any()
				.addMethod("done()").last()
			.endBlock()
		.build();

		return builder;
	}
}