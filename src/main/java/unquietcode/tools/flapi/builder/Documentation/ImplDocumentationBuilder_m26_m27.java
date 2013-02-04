
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
 * Generated on February 04, 2013 10:10:17 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 04, 2013 10:10:17 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDocumentationBuilder_m26_m27
    implements DocumentationBuilder_m26_m27, BuilderImplementation
{

    private final DocumentationHelper _helper;
    private final Object _returnValue;

    public ImplDocumentationBuilder_m26_m27(DocumentationHelper helper, Object returnValue) {
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

    /**
     * add more content to the Javadocs
     * 
     */
    public DocumentationBuilder_m26_m27 addContent(String content) {
        _helper.addContent(content);
         
        return this;
    }

    /**
     * finish writing the documentation
     * 
     */
    public Object finish() {
        _checkInvocations();
        _helper.finish();
         
        return _returnValue;
    }

}
