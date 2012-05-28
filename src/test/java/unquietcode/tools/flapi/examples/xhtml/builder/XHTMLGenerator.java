
package unquietcode.tools.flapi.examples.xhtml.builder;

import org.w3c.dom.Document;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:15:58 CDT using version 0.2
 * 
 */
public class XHTMLGenerator {


    @SuppressWarnings("unchecked")
    public static XHTMLBuilder<Document> createDocument(XHTMLHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplXHTMLBuilder(helper, helper._getReturnValue());
    }

}
