
package unquietcode.tools.flapi.examples.xhtml.builder;

import javax.annotation.Generated;


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
public class XHTMLGenerator {


    @SuppressWarnings("unchecked")
    public static XHTMLBuilder<Void> createDocument(XHTMLHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplXHTMLBuilder(helper, null);
    }

}
