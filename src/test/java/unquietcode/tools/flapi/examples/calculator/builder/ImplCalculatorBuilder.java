
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
public class ImplCalculatorBuilder
    implements CalculatorBuilder
{

    private final CalculatorHelper _helper;
    private final Object _returnValue;

    ImplCalculatorBuilder(CalculatorHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public CalculationBuilder $(int startingValue) {
        ObjectWrapper<CalculationHelper> helper1 = new ObjectWrapper<CalculationHelper>();
        _checkInvocations();
        _helper.$(startingValue, helper1);
         
        CalculationBuilder step1 = new ImplCalculationBuilder(helper1 .get(), _returnValue);
        CalculationBuilder retval = step1;
        return retval;
    }

}
