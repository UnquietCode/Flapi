
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

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
public class ImplElementBuilder_endElement
    implements ElementBuilder_endElement<Object> , BuilderImplementation
{
    private final ElementHelper _helper;
    private final Object _returnValue;

    public ImplElementBuilder_endElement(ElementHelper helper, Object returnValue) {
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

    public ElementBuilder_endElement addAttribute(String key, String value) {
        _helper.addAttribute(key, value);
         
        return this;
    }

    public ElementBuilder_endElement addComment(String comment) {
        _helper.addComment(comment);
         
        return this;
    }

    public Object endElement() {
        _checkInvocations();
        _helper.endElement();
         
        return _returnValue;
    }

    public ElementBuilder_endElement_setValue startElement(String tagName) {
        ObjectWrapper<ElementHelper> helper1 = new ObjectWrapper<ElementHelper>();
        _helper.startElement(tagName, helper1);
        ImplElementBuilder_endElement_setValue step1 = new ImplElementBuilder_endElement_setValue(helper1 .get(), this);
         
        return step1;
    }
}
