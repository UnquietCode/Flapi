
package unquietcode.tools.flapi.builder.Documentation;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on January 27, 2013 22:49:58 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 27, 2013 22:49:58 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDocumentationBuilder_m22_m23
    implements DocumentationBuilder_m22_m23, BuilderImplementation
{

    private final DocumentationHelper _helper;
    private final Object _returnValue;

    public ImplDocumentationBuilder_m22_m23(DocumentationHelper helper, Object returnValue) {
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

    public DocumentationBuilder_m22_m23 addContent(String content) {
        _helper.addContent(content);
         
        return this;
    }

    public Object finish() {
        _checkInvocations();
        _helper.finish();
         
        return _returnValue;
    }

}
