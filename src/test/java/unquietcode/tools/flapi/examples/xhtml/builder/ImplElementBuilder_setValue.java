
package unquietcode.tools.flapi.examples.xhtml.builder;

import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
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
public class ImplElementBuilder_setValue
    implements ElementBuilder_setValue, BuilderImplementation
{

    private final ElementHelper _helper;
    private final Object _returnValue;

    ImplElementBuilder_setValue(ElementHelper helper, Object returnValue) {
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

    public ElementBuilder_setValue startElement(String tagName) {
        ObjectWrapper<ElementHelper> helper1 = new ObjectWrapper<ElementHelper>();
        _helper.startElement(tagName, helper1);
        ImplElementBuilder_setValue step1 = new ImplElementBuilder_setValue(helper1 .get(), this);
         
        return step1;
    }

    public ElementBuilder_setValue addAttribute(String key, String value) {
        _helper.addAttribute(key, value);
         
        return this;
    }

    public ElementBuilder setValue(String value) {
        _helper.setValue(value);
        ImplElementBuilder step1 = new ImplElementBuilder(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public ElementBuilder_setValue addComment(String comment) {
        _helper.addComment(comment);
         
        return this;
    }

    public Object endElement() {
        _checkInvocations();
        _helper.endElement();
         
        return _returnValue;
    }

}
