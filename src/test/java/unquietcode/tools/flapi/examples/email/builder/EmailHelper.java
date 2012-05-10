
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
 * Generated on May 09, 2012 23:29:25 CDT using version 0.1
 * 
 */
public interface EmailHelper {


    EmailMessage _getReturnValue();

    void addBCC(String emailAddress);

    void addCC(String emailAddress);

    void setSubject(String subject);

    void setBody(String text);

    void setSender(String emailAddress);

    void send();

    void addRecipient(String emailAddress);

    void addAttachment(File file);

}
