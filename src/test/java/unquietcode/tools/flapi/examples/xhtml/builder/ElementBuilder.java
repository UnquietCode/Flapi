
package unquietcode.tools.flapi.examples.xhtml.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:15:58 CDT using version 0.2
 * 
 */
public interface ElementBuilder<_ReturnType >{


    ElementBuilder<_ReturnType> addAttribute(String key, String value);

    ElementBuilder<_ReturnType> addComment(String comment);

    _ReturnType endElement();

    ElementBuilder_setValue<ElementBuilder<_ReturnType>> startElement(String tagName);

}
