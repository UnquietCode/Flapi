
package unquietcode.tools.flapi.examples.xhtml.builder;

import javax.annotation.Generated;
import org.w3c.dom.Document;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface XHTMLBuilder<_ReturnType >{


    XHTMLBuilder<_ReturnType> addComment(String comment);

    Document done();

    ElementBuilder_setValue<XHTMLBuilder<_ReturnType>> startElement(String tagName);

}
