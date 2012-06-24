
package unquietcode.tools.flapi.examples.xhtml.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


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
public class ImplElementBuilder_setValue
    implements ElementBuilder_setValue, BuilderImplementation
{

    private final ElementHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplElementBuilder_setValue(ElementHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public ElementBuilder_setValue addAttribute(String key, String value) {
        _helper.addAttribute(key, value);
         
        return this;
    }

    public ElementBuilder_setValue addComment(String comment) {
        _helper.addComment(comment);
         
        return this;
    }

    public BuilderImplementation endElement() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.endElement();
         
        return _returnValue;
    }

    public ElementBuilder_setValue startElement(String tagName) {
        ObjectWrapper<ElementHelper> helper1 = new ObjectWrapper<ElementHelper>();
        _helper.startElement(tagName, helper1);
         
        ImplElementBuilder_setValue step1 = new ImplElementBuilder_setValue(helper1 .get(), this);
        _transferInvocations(step1);
        return step1;
    }

    public ElementBuilder setValue(String value) {
        _helper.setValue(value);
         
        ElementBuilder retval = new ImplElementBuilder(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
