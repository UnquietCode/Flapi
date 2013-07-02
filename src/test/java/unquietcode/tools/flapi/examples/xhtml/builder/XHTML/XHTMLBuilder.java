
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder_endElement_setValue;
import unquietcode.tools.flapi.support.MethodInfo;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 21:53:50 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 21:53:50 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface XHTMLBuilder<_ReturnType> {
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {

    })
    XHTMLBuilder<_ReturnType> addComment(String comment);

    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = 2, chain = {

    })
    Document done();

    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {
        ElementBuilder_endElement_setValue.class
    })
    ElementBuilder_endElement_setValue<XHTMLBuilder<_ReturnType>> startElement(String tagName);

    public interface $<_ReturnType>
        extends XHTMLBuilder<_ReturnType>
    {

    }
}
