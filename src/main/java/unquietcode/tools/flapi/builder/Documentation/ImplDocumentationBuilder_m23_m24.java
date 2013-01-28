
package unquietcode.tools.flapi.builder.Documentation;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on January 29, 2013 21:38:13 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 29, 2013 21:38:13 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDocumentationBuilder_m23_m24
    implements DocumentationBuilder_m23_m24, BuilderImplementation
{

    private final DocumentationHelper _helper;
    private final Object _returnValue;

    public ImplDocumentationBuilder_m23_m24(DocumentationHelper helper, Object returnValue) {
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

    public DocumentationBuilder_m23_m24 addContent(String content) {
        _helper.addContent(content);
         
        return this;
    }

    public Object finish() {
        _checkInvocations();
        _helper.finish();
         
        return _returnValue;
    }

}
