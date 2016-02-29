
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper;

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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface XHTMLHelper {
    void addComment(String comment);

    Document done();

    void startElement(String tagName, AtomicReference<ElementHelper> _helper1);
}
