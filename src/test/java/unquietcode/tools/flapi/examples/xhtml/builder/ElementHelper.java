
package unquietcode.tools.flapi.examples.xhtml.builder;

import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

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
public interface ElementHelper {


    void startElement(String tagName, ObjectWrapper<ElementHelper> _helper1);

    void addAttribute(String key, String value);

    void setValue(String value);

    void addComment(String comment);

    void endElement();

}
