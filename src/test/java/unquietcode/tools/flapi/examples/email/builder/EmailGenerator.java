
package unquietcode.tools.flapi.examples.email.builder;

import unquietcode.tools.flapi.DescriptorBuilderException;
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
public class EmailGenerator {


    @SuppressWarnings("unchecked")
    public static EmailBuilder_setBody_setSender_setSubject<EmailMessage> compose(EmailHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplEmailBuilder_setBody_setSender_setSubject(helper, helper._getReturnValue());
    }

}
