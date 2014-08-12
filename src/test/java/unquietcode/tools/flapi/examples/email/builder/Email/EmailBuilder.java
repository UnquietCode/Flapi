
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
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailBuilder {
    public interface Start<_ReturnType>
        extends EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f_2body_4f_2sender_4f_2subject_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Recursive)
        EmailBuilder.Start<_ReturnType> addAttachment(File file);

        @MethodInfo(type = TransitionType.Recursive)
        EmailBuilder.Start<_ReturnType> addBCC(String emailAddress);

        @MethodInfo(type = TransitionType.Recursive)
        EmailBuilder.Start<_ReturnType> addCC(String emailAddress);

        @MethodInfo(type = TransitionType.Recursive)
        @Tracked(atLeast = 1, key = "addRecipient$String_emailAddress")
        EmailBuilder.Start<_ReturnType> addRecipient(String emailAddress);

        @MethodInfo(type = TransitionType.Lateral)
        EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f_2sender_4f_2subject_4f<_ReturnType> body(String text);

        @MethodInfo(type = TransitionType.Terminal)
        EmailMessage send();

        @MethodInfo(type = TransitionType.Lateral)
        @Tracked(atLeast = 1, key = "sender$String_emailAddress")
        EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f_2body_4f_2subject_4f<_ReturnType> sender(String emailAddress);

        @MethodInfo(type = TransitionType.Lateral)
        EmailBuilder_2addAttachment_4f_2addBCC_4f_2addCC_4f_2addRecipient_4f_2body_4f_2sender_4f<_ReturnType> subject(String subject);
    }
}
