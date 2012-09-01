
package unquietcode.tools.flapi.examples.xhtml.builder;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on September 01, 2012 17:06:14 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "September 01, 2012 17:06:14 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ElementBuilder<_ReturnType >{


    _ReturnType endElement();

    ElementBuilder<_ReturnType> addComment(String comment);

    ElementBuilder<_ReturnType> addAttribute(String key, String value);

    ElementBuilder_setValue<ElementBuilder<_ReturnType>> startElement(String tagName);

}
