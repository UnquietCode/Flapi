
package unquietcode.tools.flapi.examples.calculator.builder;

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
public class ImplCalculationBuilder
    implements CalculationBuilder, BuilderImplementation
{

    private final CalculationHelper _helper;
    private final Object _returnValue;

    ImplCalculationBuilder(CalculationHelper helper, Object returnValue) {
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

    public CalculationBuilder times(int value) {
        _helper.times(value);
         
        return this;
    }

    public CalculationBuilder divide(int value) {
        _helper.divide(value);
         
        return this;
    }

    public CalculationBuilder power(int value) {
        _helper.power(value);
         
        return this;
    }

    public CalculationBuilder abs() {
        _helper.abs();
         
        return this;
    }

    public CalculationBuilder plus(int value) {
        _helper.plus(value);
         
        return this;
    }

    public ObjectWrapper equals() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        ObjectWrapper intermediateResult = _helper.equals();
         
        return intermediateResult;
    }

    public CalculationBuilder minus(int value) {
        _helper.minus(value);
         
        return this;
    }

    public CalculationBuilder mod(int value) {
        _helper.mod(value);
         
        return this;
    }

}
