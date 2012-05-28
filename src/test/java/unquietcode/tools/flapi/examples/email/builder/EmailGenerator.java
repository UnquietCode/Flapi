
package unquietcode.tools.flapi.examples.email.builder;

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
public class EmailGenerator {


    @SuppressWarnings("unchecked")
    public static EmailBuilder_body_sender_subject<EmailMessage> compose(EmailHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplEmailBuilder_body_sender_subject(helper, helper._getReturnValue());
    }

}
