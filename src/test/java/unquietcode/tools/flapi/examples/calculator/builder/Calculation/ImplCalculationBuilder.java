
package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplCalculationBuilder
    implements CalculationBuilder<Object> , BuilderImplementation
{
    private final CalculationHelper _helper;
    private final Object _returnValue;

    public ImplCalculationBuilder(CalculationHelper helper, Object returnValue) {
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

    public CalculationBuilder abs() {
        _helper.abs();
         
        return this;
    }

    public CalculationBuilder divide(int value) {
        _helper.divide(value);
         
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

    public CalculationBuilder plus(int value) {
        _helper.plus(value);
         
        return this;
    }

    public CalculationBuilder power(int value) {
        _helper.power(value);
         
        return this;
    }

    public CalculationBuilder times(int value) {
        _helper.times(value);
         
        return this;
    }
}
