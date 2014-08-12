
package unquietcode.tools.flapi.examples.email.builder.Email;

import unquietcode.tools.flapi.examples.email.EmailMessage;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.Tracked;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;
import java.io.File;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 12, 2014 13:17:30 PDT using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.email.builder.Email.EmailHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f<_ReturnType> addAttachment(File file);

    @MethodInfo(type = TransitionType.Recursive)
    EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f<_ReturnType> addBCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive)
    EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f<_ReturnType> addCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive)
    @Tracked(atLeast = 1, key = "addRecipient$String_emailAddress")
    EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f<_ReturnType> addRecipient(String emailAddress);

    @MethodInfo(type = TransitionType.Terminal)
    EmailMessage send();
}
