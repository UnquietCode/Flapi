
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder_endElement_setValue;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface XHTMLBuilder<_ReturnType> {
    XHTMLBuilder<_ReturnType> addComment(String comment);

    Document done();

    ElementBuilder_endElement_setValue<XHTMLBuilder<_ReturnType>> startElement(String tagName);

    public interface $<_ReturnType>
        extends XHTMLBuilder<_ReturnType>
    {

    }
}
