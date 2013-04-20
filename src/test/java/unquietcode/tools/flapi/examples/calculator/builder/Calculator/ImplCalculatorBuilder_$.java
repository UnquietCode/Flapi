
package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationHelper;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.ImplCalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder.$;
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
public class ImplCalculatorBuilder_$
    implements $<Object> , CalculatorBuilder_$<Object> , BuilderImplementation
{
    private final CalculatorHelper _helper;
    private final Object _returnValue;

    public ImplCalculatorBuilder_$(CalculatorHelper helper, Object returnValue) {
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

    public CalculationBuilder $(int startingValue) {
        _checkInvocations();
        ObjectWrapper<CalculationHelper> helper1 = new ObjectWrapper<CalculationHelper>();
        _helper.$(startingValue, helper1);
        ImplCalculationBuilder step1 = new ImplCalculationBuilder(helper1 .get(), _returnValue);
         
        return step1;
    }
}
