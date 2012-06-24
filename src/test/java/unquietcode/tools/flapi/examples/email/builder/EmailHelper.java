
package unquietcode.tools.flapi.examples.email.builder;

import java.io.File;
import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.email.EmailMessage;


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
public interface EmailHelper {


    void addBCC(String emailAddress);

    EmailMessage send();

    void body(String text);

    void addRecipient(String emailAddress);

    void addAttachment(File file);

    void sender(String emailAddress);

    void addCC(String emailAddress);

    void subject(String subject);

}
