
package unquietcode.tools.flapi.examples.email.builder;

import unquietcode.tools.flapi.examples.email.EmailMessage;

import javax.annotation.Generated;
import java.io.File;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on September 01, 2012 17:06:14 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "September 01, 2012 17:06:14 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface EmailBuilder_sender_subject<_ReturnType >{


    EmailBuilder_sender<_ReturnType> subject(String subject);

    EmailBuilder_sender_subject<_ReturnType> addAttachment(File file);

    EmailBuilder_sender_subject<_ReturnType> addRecipient(String emailAddress);

    EmailMessage send();

    EmailBuilder_sender_subject<_ReturnType> addBCC(String emailAddress);

    EmailBuilder_subject<_ReturnType> sender(String emailAddress);

    EmailBuilder_sender_subject<_ReturnType> addCC(String emailAddress);

}
