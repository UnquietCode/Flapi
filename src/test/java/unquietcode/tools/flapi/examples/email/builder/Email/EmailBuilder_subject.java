
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
public interface EmailBuilder_subject<_ReturnType >{


    EmailBuilder_subject<_ReturnType> addAttachment(File file);

    EmailBuilder_subject<_ReturnType> addBCC(String emailAddress);

    EmailBuilder_subject<_ReturnType> addCC(String emailAddress);

    EmailBuilder_subject<_ReturnType> addRecipient(String emailAddress);

    EmailMessage send();

    EmailBuilder<_ReturnType> subject(String subject);

}
