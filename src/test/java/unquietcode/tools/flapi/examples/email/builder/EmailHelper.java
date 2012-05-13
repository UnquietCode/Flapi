
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
 * Generated on May 13, 2012 24:29:29 CDT using version 0.2
 * 
 */
public interface EmailHelper {


    EmailMessage _getReturnValue();

    void addCC(String emailAddress);

    void setSender(String emailAddress);

    void addRecipient(String emailAddress);

    void send();

    void setBody(String text);

    void setSubject(String subject);

    void addBCC(String emailAddress);

    void addAttachment(File file);

}
