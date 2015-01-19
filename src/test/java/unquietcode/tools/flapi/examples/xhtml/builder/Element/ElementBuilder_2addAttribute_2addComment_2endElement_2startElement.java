
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder.Start;
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
 * Generated using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper
 */
@Generated(value = "unquietcode.tools.flapi", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType> addAttribute(String key, String value);

    @MethodInfo(type = TransitionType.Recursive)
    ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Ascending)
    _ReturnType endElement();

    @MethodInfo(type = TransitionType.Recursive, chainInfo = {
        @ChainInfo(type = Start.class, position = 1)
    })
    Start<ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType>> startElement(String tagName);
}
