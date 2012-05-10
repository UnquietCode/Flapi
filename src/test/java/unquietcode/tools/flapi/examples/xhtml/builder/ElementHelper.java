
package unquietcode.tools.flapi.examples.xhtml.builder;

import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:44:26 CDT using version 0.1
 * 
 */
public interface ElementHelper {


    void addComment(String comment);

    void endElement();

    void startElement(String tagName, ObjectWrapper<ElementHelper> _helper1);

    void addAttribute(String key, String value);

    void setValue(String value);

}
