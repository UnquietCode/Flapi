
package unquietcode.tools.flapi.examples.email.builder;

import java.io.File;
import java.lang.reflect.Field;
import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.email.EmailMessage;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ExpectedInvocationsException;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEmailBuilder_subject
    implements EmailBuilder_subject, BuilderImplementation
{

    private final EmailHelper _helper;
    private final BuilderImplementation _returnValue;
    int ic_Email_addRecipient$String_emailAddress = 1;
    int ic_Email_sender$String_emailAddress = 1;

    ImplEmailBuilder_subject(EmailHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
    }

    private void _transferInvocations(Object next) {
        Class clazz = next.getClass();
         
        try {
            Field field = clazz.getDeclaredField("ic_Email_addRecipient$String_emailAddress");
            field.setInt(next, ic_Email_addRecipient$String_emailAddress);
        } catch (Exception _x) {
            // nothing
        }
         
        try {
            Field field = clazz.getDeclaredField("ic_Email_sender$String_emailAddress");
            field.setInt(next, ic_Email_sender$String_emailAddress);
        } catch (Exception _x) {
            // nothing
        }
    }

    public void _checkInvocations() {
        if (ic_Email_addRecipient$String_emailAddress > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'addRecipient(String emailAddress)'.");
        }
        if (ic_Email_sender$String_emailAddress > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'sender(String emailAddress)'.");
        }
    }

    public EmailBuilder_subject addAttachment(File file) {
        _helper.addAttachment(file);
         
        return this;
    }

    public EmailBuilder_subject addBCC(String emailAddress) {
        _helper.addBCC(emailAddress);
         
        return this;
    }

    public EmailBuilder_subject addCC(String emailAddress) {
        _helper.addCC(emailAddress);
         
        return this;
    }

    public EmailBuilder_subject addRecipient(String emailAddress) {
        --ic_Email_addRecipient$String_emailAddress;
        _helper.addRecipient(emailAddress);
         
        return this;
    }

    public EmailMessage send() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        EmailMessage intermediateResult = _helper.send();
         
        return intermediateResult;
    }

    public EmailBuilder subject(String subject) {
        _helper.subject(subject);
         
        EmailBuilder retval = new ImplEmailBuilder(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
