
package unquietcode.tools.flapi.examples.email.builder.Email;

import unquietcode.tools.flapi.examples.email.EmailMessage;

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
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailBuilder_sender_subject<_ReturnType> {
    EmailBuilder_sender_subject<_ReturnType> addAttachment(File file);

    EmailBuilder_sender_subject<_ReturnType> addBCC(String emailAddress);

    EmailBuilder_sender_subject<_ReturnType> addCC(String emailAddress);

    EmailBuilder_sender_subject<_ReturnType> addRecipient(String emailAddress);

    EmailMessage send();

    EmailBuilder_subject<_ReturnType> sender(String emailAddress);

    EmailBuilder_sender<_ReturnType> subject(String subject);
}
