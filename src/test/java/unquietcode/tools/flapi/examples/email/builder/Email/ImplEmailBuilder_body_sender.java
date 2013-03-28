
package unquietcode.tools.flapi.examples.email.builder.Email;

import java.io.File;
import java.lang.reflect.Field;
import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.email.EmailMessage;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ExpectedInvocationsException;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on March 28, 2013 10:17:59 PDT using version 0.4
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "March 28, 2013 10:17:59 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEmailBuilder_body_sender
    implements EmailBuilder_body_sender, BuilderImplementation
{

    private final EmailHelper _helper;
    private final Object _returnValue;
    int ic_Email_addRecipient$String_emailAddress = 1;
    int ic_Email_sender$String_emailAddress = 1;

    public ImplEmailBuilder_body_sender(EmailHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
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

    public EmailBuilder_body_sender addAttachment(File file) {
        _helper.addAttachment(file);
         
        return this;
    }

    public EmailBuilder_body_sender addBCC(String emailAddress) {
        _helper.addBCC(emailAddress);
         
        return this;
    }

    public EmailBuilder_body_sender addCC(String emailAddress) {
        _helper.addCC(emailAddress);
         
        return this;
    }

    public EmailBuilder_body_sender addRecipient(String emailAddress) {
        --ic_Email_addRecipient$String_emailAddress;
        _helper.addRecipient(emailAddress);
         
        return this;
    }

    public EmailBuilder_sender body(String text) {
        _helper.body(text);
        ImplEmailBuilder_sender step1 = new ImplEmailBuilder_sender(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public EmailMessage send() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        EmailMessage intermediateResult = _helper.send();
         
        return intermediateResult;
    }

    public EmailBuilder_body sender(String emailAddress) {
        --ic_Email_sender$String_emailAddress;
        _helper.sender(emailAddress);
        ImplEmailBuilder_body step1 = new ImplEmailBuilder_body(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
