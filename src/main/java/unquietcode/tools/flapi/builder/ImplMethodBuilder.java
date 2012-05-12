
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 12, 2012 24:38:04 CDT using version 0.1
 * 
 */
public class ImplMethodBuilder
    implements MethodBuilder
{

    private final MethodHelper _helper;
    private final Object _returnValue;

    ImplMethodBuilder(MethodHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public Object any() {
        _checkInvocations();
        _helper.any();
         
        Object retval = _returnValue;
        return retval;
    }

    public Object atLeast(int num) {
        _checkInvocations();
        _helper.atLeast(num);
         
        Object retval = _returnValue;
        return retval;
    }

    public Object atMost(int num) {
        _checkInvocations();
        _helper.atMost(num);
         
        Object retval = _returnValue;
        return retval;
    }

    public Object between(int atLeast, int atMost) {
        _checkInvocations();
        _helper.between(atLeast, atMost);
         
        Object retval = _returnValue;
        return retval;
    }

    public Object last() {
        _checkInvocations();
        _helper.last();
         
        Object retval = _returnValue;
        return retval;
    }

    public Object once() {
        _checkInvocations();
        _helper.once();
         
        Object retval = _returnValue;
        return retval;
    }

}
