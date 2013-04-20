
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

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
public interface ElementBuilder_endElement<_ReturnType> {
    ElementBuilder_endElement<_ReturnType> addAttribute(String key, String value);

    ElementBuilder_endElement<_ReturnType> addComment(String comment);

    _ReturnType endElement();

    ElementBuilder_endElement_setValue<ElementBuilder_endElement<_ReturnType>> startElement(String tagName);
}
