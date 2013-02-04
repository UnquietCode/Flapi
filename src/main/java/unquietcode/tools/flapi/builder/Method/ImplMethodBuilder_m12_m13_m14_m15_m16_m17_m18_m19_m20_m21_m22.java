
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Documentation.DocumentationBuilder_m26_m27;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.builder.Documentation.ImplDocumentationBuilder_m26_m27;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public class ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22
    implements MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22, BuilderImplementation
{

    private final MethodHelper _helper;
    private final Object _returnValue;

    public ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22(MethodHelper helper, Object returnValue) {
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
     * expect the method [0, inf) times
     * 
     */
    public Object any() {
        _checkInvocations();
        _helper.any();
         
        return _returnValue;
    }

    /**
     * expect the method [X, inf) times
     * 
     */
    public Object atLeast(int num) {
        _checkInvocations();
        _helper.atLeast(num);
         
        return _returnValue;
    }

    /**
     * expect the method [0, X] times
     * 
     */
    public Object atMost(int num) {
        _checkInvocations();
        _helper.atMost(num);
         
        return _returnValue;
    }

    /**
     * expect the method [0, X] times, and assign a group number
     * 
     */
    public Object atMost(int num, int group) {
        _checkInvocations();
        _helper.atMost(num, group);
         
        return _returnValue;
    }

    /**
     * expect the method [atLeast, atMost] times
     * 
     */
    public Object between(int atLeast, int atMost) {
        _checkInvocations();
        _helper.between(atLeast, atMost);
         
        return _returnValue;
    }

    /**
     * expect the method [X, X] times
     * 
     */
    public Object exactly(int num) {
        _checkInvocations();
        _helper.exactly(num);
         
        return _returnValue;
    }

    /**
     * mark the method as terminal, exiting the block when called
     * 
     */
    public Object last() {
        _checkInvocations();
        _helper.last();
         
        return _returnValue;
    }

    /**
     * mark the method as terminal, returning an object of the given type when called
     * 
     */
    public Object last(Class returnType) {
        _checkInvocations();
        _helper.last(returnType);
         
        return _returnValue;
    }

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     * 
     */
    public MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m21_m22 markAsDeprecated(String reason) {
        _helper.markAsDeprecated(reason);
        ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m21_m22 step1 = new ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m21_m22(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * Add javadoc style documentation to the method.
     * 
     */
    public DocumentationBuilder_m26_m27 withDocumentation() {
        ObjectWrapper<DocumentationHelper> helper1 = new ObjectWrapper<DocumentationHelper>();
        _helper.withDocumentation(helper1);
        ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20 step1 = new ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20(_helper, _returnValue);
        ImplDocumentationBuilder_m26_m27 step2 = new ImplDocumentationBuilder_m26_m27(helper1 .get(), step1);
         
        _transferInvocations(step2);
        return step2;
    }

    /**
     * Add javadoc style documentation to the method.
     * 
     */
    public MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20 withDocumentation(String documentation) {
        _helper.withDocumentation(documentation);
        ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20 step1 = new ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
