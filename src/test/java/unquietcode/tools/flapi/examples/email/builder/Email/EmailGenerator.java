
package unquietcode.tools.flapi.examples.email.builder.Email;

import unquietcode.tools.flapi.examples.email.builder.Email.EmailBuilder.$;
import unquietcode.tools.flapi.support.BlockInvocationHandler;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 22:50:05 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 22:50:05 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class EmailGenerator {
    @SuppressWarnings("unchecked")
    public static $<Void> compose(EmailHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new BlockInvocationHandler(helper, null)._proxy($.class);
    }
}
