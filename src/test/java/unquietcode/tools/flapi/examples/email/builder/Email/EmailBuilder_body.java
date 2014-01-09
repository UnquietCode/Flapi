
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

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
public interface EmailBuilder_body<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder_body<_ReturnType> addAttachment(File file);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder_body<_ReturnType> addBCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    EmailBuilder_body<_ReturnType> addCC(String emailAddress);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    @Tracked(atLeast = 1, key = "Email_addRecipient$String_emailAddress")
    EmailBuilder_body<_ReturnType> addRecipient(String emailAddress);

    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    EmailBuilder<_ReturnType> body(String text);

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    EmailMessage send();
}
