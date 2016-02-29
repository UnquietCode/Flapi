
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.runtime.ChainInfo;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface XHTMLBuilder_2addComment_2startElement<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLBuilder.Head<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Terminal)
    Document done();

    @MethodInfo(type = TransitionType.Recursive, chainInfo = {
        @ChainInfo(type = unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder.Head.class, position = 1)
    })
    unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder.Head<unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLBuilder.Head<_ReturnType>> startElement(String tagName);
}
