
package unquietcode.tools.flapi.builder.Method;

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
 * Generated on February 02, 2013 13:11:40 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 13:11:40 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20
    implements MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20, BuilderImplementation
{

    private final MethodHelper _helper;
    private final Object _returnValue;

    public ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20(MethodHelper helper, Object returnValue) {
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
     * expect the method [0, inf) times, and assign a group number
     * 
     */
    public Object any(int group) {
        _checkInvocations();
        _helper.any(group);
         
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

}
