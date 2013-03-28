
package unquietcode.tools.flapi.examples.email.builder.Email;

import javax.annotation.Generated;


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
public class EmailGenerator {


    @SuppressWarnings("unchecked")
    public static unquietcode.tools.flapi.examples.email.builder.Email.EmailBuilder.$<Void> compose(EmailHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplEmailBuilder_body_sender_subject(helper, null);
    }

}
