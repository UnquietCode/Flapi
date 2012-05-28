
package unquietcode.tools.flapi.examples.email.builder;

import java.io.File;
import unquietcode.tools.flapi.examples.email.EmailMessage;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:14:26 CDT using version 0.2
 * 
 */
public interface EmailHelper {


    EmailMessage _getReturnValue();

    void addCC(String emailAddress);

    void sender(String emailAddress);

    void addRecipient(String emailAddress);

    void send();

    void body(String text);

    void subject(String subject);

    void addAttachment(File file);

    void addBCC(String emailAddress);

}
