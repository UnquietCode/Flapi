
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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder<_ReturnType> addAttachment(File file);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder<_ReturnType> addBCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder<_ReturnType> addCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    @Tracked(atLeast = 1, key = "Email_addRecipient$String_emailAddress")
    EmailBuilder<_ReturnType> addRecipient(String emailAddress);

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    EmailMessage send();

    public interface $<_ReturnType>
        extends EmailBuilder_body_sender_subject<_ReturnType>
    {

    }
}
