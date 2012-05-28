
package unquietcode.tools.flapi.examples.calculator.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:12:35 CDT using version 0.2
 * 
 */
public class ImplCalculationBuilder
    implements CalculationBuilder
{

    private final CalculationHelper _helper;
    private final Object _returnValue;

    ImplCalculationBuilder(CalculationHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public CalculationBuilder abs() {
        _helper.abs();
         
        CalculationBuilder retval = this;
        return retval;
    }

    public CalculationBuilder divide(int value) {
        _helper.divide(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

    public Object equals() {
        _checkInvocations();
        _helper.equals();
         
        Object retval = _returnValue;
        return retval;
    }

    public CalculationBuilder minus(int value) {
        _helper.minus(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

    public CalculationBuilder mod(int value) {
        _helper.mod(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

    public CalculationBuilder plus(int value) {
        _helper.plus(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

    public CalculationBuilder power(int value) {
        _helper.power(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

    public CalculationBuilder times(int value) {
        _helper.times(value);
         
        CalculationBuilder retval = this;
        return retval;
    }

}
