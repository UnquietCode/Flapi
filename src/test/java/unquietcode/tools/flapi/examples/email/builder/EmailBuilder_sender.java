
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
public interface EmailBuilder_sender<_ReturnType >{


    EmailBuilder_sender<_ReturnType> addAttachment(File file);

    EmailBuilder_sender<_ReturnType> addBCC(String emailAddress);

    EmailBuilder_sender<_ReturnType> addCC(String emailAddress);

    EmailBuilder_sender<_ReturnType> addRecipient(String emailAddress);

    EmailMessage send();

    EmailBuilder<_ReturnType> sender(String emailAddress);

}
