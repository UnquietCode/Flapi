
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.xhtml.builder.Element;

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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ElementBuilder_endElement<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    ElementBuilder_endElement<_ReturnType> addAttribute(String key, String value);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    ElementBuilder_endElement<_ReturnType> addComment(String comment);

    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType endElement();

    @MethodInfo(type = TransitionType.Recursive, chain = {
        ElementBuilder_endElement_setValue.class
    })
    ElementBuilder_endElement_setValue<ElementBuilder_endElement<_ReturnType>> startElement(String tagName);
}
