
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

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
 * Generated on July 01, 2013 22:50:06 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 22:50:06 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ElementBuilder_endElement<_ReturnType> {
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = TransitionType.Recursive, chain = {

    })
    ElementBuilder_endElement<_ReturnType> addAttribute(String key, String value);

    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = TransitionType.Recursive, chain = {

    })
    ElementBuilder_endElement<_ReturnType> addComment(String comment);

    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = TransitionType.Ascending, chain = {

    })
    _ReturnType endElement();

    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = TransitionType.Recursive, chain = {
        ElementBuilder_endElement_setValue.class
    })
    ElementBuilder_endElement_setValue<ElementBuilder_endElement<_ReturnType>> startElement(String tagName);
}
