
package unquietcode.tools.flapi.examples.xhtml.builder;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.DescriptorBuilderException;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:44:26 CDT using version 0.1
 * 
 */
public class XHTMLGenerator {


    @SuppressWarnings("unchecked")
    public static XHTMLBuilder<Document> createDocument(XHTMLHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplXHTMLBuilder(helper, helper._getReturnValue());
    }

}
