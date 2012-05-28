
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
public class ImplElementBuilder
    implements ElementBuilder
{

    private final ElementHelper _helper;
    private final Object _returnValue;

    ImplElementBuilder(ElementHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public ElementBuilder addAttribute(String key, String value) {
        _helper.addAttribute(key, value);
         
        ElementBuilder retval = this;
        return retval;
    }

    public ElementBuilder addComment(String comment) {
        _helper.addComment(comment);
         
        ElementBuilder retval = this;
        return retval;
    }

    public Object endElement() {
        _checkInvocations();
        _helper.endElement();
         
        Object retval = _returnValue;
        return retval;
    }

    public ElementBuilder_setValue startElement(String tagName) {
        ObjectWrapper<ElementHelper> helper1 = new ObjectWrapper<ElementHelper>();
        _helper.startElement(tagName, helper1);
         
        ElementBuilder_setValue step1 = new ImplElementBuilder_setValue(helper1 .get(), this);
        ElementBuilder_setValue retval = step1;
        _transferInvocations(retval);
        return retval;
    }

}
