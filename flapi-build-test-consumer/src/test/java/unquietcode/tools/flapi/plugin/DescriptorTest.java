package unquietcode.tools.flapi.plugin;

import org.junit.Assert;
import org.junit.Test;
import unquietcode.tools.flapi.plugin.test.builder.ABlock.ABlockHelper;
import unquietcode.tools.flapi.plugin.test.builder.Email.EmailGenerator;
import unquietcode.tools.flapi.plugin.test.builder.Email.EmailHelper;
import unquietcode.tools.flapi.plugin.test.builder.TestEnum.TestEnumHelper;

import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

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

			.block()
				.test().One()
				.test().TWO()
			.done()
		.send();
	}

    @Test
    public void annotation() throws NoSuchMethodException {
        Method method = EmailHelper.class.getMethod("block", AtomicReference.class);
        Assert.assertNotNull("missing method", method);

        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
        Assert.assertNotNull("missing annotation", annotation);
        Assert.assertEquals("missing value", "a", annotation.someValue());
//        Assert.assertEquals("missing value", 2, annotation.someValues().length);
//        Assert.assertEquals("missing value", "a", annotation.someValues()[0]);
//        Assert.assertEquals("missing value", "b", annotation.someValues()[1]);
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

		@Override
		public void block(AtomicReference<ABlockHelper> _helper1) {
			_helper1.set(new ABlockHelper() {
				@Override
				public void done() {
					// nothing
				}

				@Override
				public void test(AtomicReference<TestEnumHelper> _helper1) {
					_helper1.set(new TestEnumHelper() {
						@Override
						public void One() {
							// nothing
						}

						@Override
						public void TWO() {
							// nothing
						}

						@Override
						public void three() {
							// nothing
						}
					});
				}
			});
		}
	};
}
