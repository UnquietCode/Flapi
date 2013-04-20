
package unquietcode.tools.flapi.examples.xhtml.builder.XHTML;

import org.w3c.dom.Document;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementBuilder_endElement_setValue;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ImplElementBuilder_endElement_setValue;
import unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLBuilder.$;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;

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
public class ImplXHTMLBuilder
    implements $<Object> , XHTMLBuilder<Object> , BuilderImplementation
{
    private final XHTMLHelper _helper;
    private final Object _returnValue;

    public ImplXHTMLBuilder(XHTMLHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public XHTMLBuilder addComment(String comment) {
        _helper.addComment(comment);
         
        return this;
    }

    public Document done() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Document intermediateResult = _helper.done();
         
        return intermediateResult;
    }

    public ElementBuilder_endElement_setValue startElement(String tagName) {
        ObjectWrapper<ElementHelper> helper1 = new ObjectWrapper<ElementHelper>();
        _helper.startElement(tagName, helper1);
        ImplElementBuilder_endElement_setValue step1 = new ImplElementBuilder_endElement_setValue(helper1 .get(), this);
         
        return step1;
    }
}
