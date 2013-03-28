
package unquietcode.tools.flapi.examples.email.builder.Email;

import java.io.File;
import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.email.EmailMessage;


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
