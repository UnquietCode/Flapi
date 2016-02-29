
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder.Head;
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
 * @see unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface ElementBuilder_2addAttribute_2addComment_2setValue_2startElement<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> addAttribute(String key, String value);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Ascending)
    _ReturnType endElement();

    @MethodInfo(type = TransitionType.Lateral)
    ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType> setValue(String value);

    @MethodInfo(type = TransitionType.Recursive, chainInfo = {
        @ChainInfo(type = Head.class, position = 1)
    })
    Head<Head<_ReturnType>> startElement(String tagName);
}
