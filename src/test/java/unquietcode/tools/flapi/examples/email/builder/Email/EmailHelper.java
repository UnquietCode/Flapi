
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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailHelper {
    void addAttachment(File file);

    void addBCC(String emailAddress);

    void addCC(String emailAddress);

    void addRecipient(String emailAddress);

    void body(String text);

    EmailMessage send();

    void sender(String emailAddress);

    void subject(String subject);
}
