
package unquietcode.tools.flapi.examples.email.builder;

import javax.annotation.Generated;


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
public class EmailGenerator {


    @SuppressWarnings("unchecked")
    public static EmailBuilder_body_sender_subject<Void> compose(EmailHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplEmailBuilder_body_sender_subject(helper, null);
    }

}
