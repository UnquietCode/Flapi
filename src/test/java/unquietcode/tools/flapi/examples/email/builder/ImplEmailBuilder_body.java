
package unquietcode.tools.flapi.examples.email.builder;

import unquietcode.tools.flapi.examples.email.EmailMessage;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ExpectedInvocationsException;

import javax.annotation.Generated;
import java.io.File;
import java.lang.reflect.Field;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on September 01, 2012 17:06:14 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "September 01, 2012 17:06:14 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplEmailBuilder_body
    implements EmailBuilder_body, BuilderImplementation
{

    private final EmailHelper _helper;
    private final Object _returnValue;
    int ic_Email_addRecipient$String_emailAddress = 1;

    ImplEmailBuilder_body(EmailHelper helper, Object returnValue) {
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
    }

    public void _checkInvocations() {
        if (ic_Email_addRecipient$String_emailAddress > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'addRecipient(String emailAddress)'.");
        }
    }

    public EmailBuilder_body addAttachment(File file) {
        _helper.addAttachment(file);
         
        return this;
    }

    public EmailBuilder_body addBCC(String emailAddress) {
        _helper.addBCC(emailAddress);
         
        return this;
    }

    public EmailBuilder_body addCC(String emailAddress) {
        _helper.addCC(emailAddress);
         
        return this;
    }

    public EmailBuilder body(String text) {
        _helper.body(text);
        ImplEmailBuilder step1 = new ImplEmailBuilder(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public EmailBuilder_body addRecipient(String emailAddress) {
        --ic_Email_addRecipient$String_emailAddress;
        _helper.addRecipient(emailAddress);
         
        return this;
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

}
