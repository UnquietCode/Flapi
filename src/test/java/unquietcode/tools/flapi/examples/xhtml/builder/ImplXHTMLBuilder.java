
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
public class ImplXHTMLBuilder
    implements XHTMLBuilder
{

    private final XHTMLHelper _helper;
    private final Object _returnValue;

    ImplXHTMLBuilder(XHTMLHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public XHTMLBuilder addComment(String comment) {
        _helper.addComment(comment);
         
        XHTMLBuilder retval = this;
        return retval;
    }

    public Object done() {
        _checkInvocations();
        _helper.done();
         
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
