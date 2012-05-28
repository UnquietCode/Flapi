
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
public interface ElementHelper {


    void addComment(String comment);

    void addAttribute(String key, String value);

    void endElement();

    void setValue(String value);

    void startElement(String tagName, ObjectWrapper<ElementHelper> _helper1);

}
