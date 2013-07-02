
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder_endElement_setValue;
import unquietcode.tools.flapi.support.MethodInfo;
import unquietcode.tools.flapi.support.TransitionType;

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
public interface XHTMLBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    XHTMLBuilder<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    Document done();

    @MethodInfo(type = TransitionType.Recursive, chain = {
        ElementBuilder_endElement_setValue.class
    })
    ElementBuilder_endElement_setValue<XHTMLBuilder<_ReturnType>> startElement(String tagName);

    public interface $<_ReturnType>
        extends XHTMLBuilder<_ReturnType>
    {

    }
}
