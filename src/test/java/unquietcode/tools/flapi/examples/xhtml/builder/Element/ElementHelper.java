
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

import javax.annotation.Generated;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 13, 2014 16:08:21 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-13T16:08:21-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface ElementHelper {
    void addAttribute(String key, String value);

    void addComment(String comment);

    void endElement();

    void setValue(String value);

    void startElement(String tagName, AtomicReference<ElementHelper> _helper1);
}
