
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLBuilder.$;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class XHTMLGenerator {
    @SuppressWarnings("unchecked")
    public static $<Void> createDocument(XHTMLHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new BlockInvocationHandler(helper, null)._proxy($.class);
    }
}
