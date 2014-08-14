
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder.Start;
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
 * Generated on August 13, 2014 16:08:21 PDT using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-13T16:08:21-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface ElementBuilder_2addAttribute_2addComment_2setValue_2startElement<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> addAttribute(String key, String value);

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Ascending)
    _ReturnType endElement();

    @MethodInfo(type = TransitionType.Lateral)
    ElementBuilder_2addAttribute_2addComment_2endElement_2startElement<_ReturnType> setValue(String value);

    @MethodInfo(type = TransitionType.Recursive, chain = {
        Start.class
    })
    Start<Start<_ReturnType>> startElement(String tagName);
}
