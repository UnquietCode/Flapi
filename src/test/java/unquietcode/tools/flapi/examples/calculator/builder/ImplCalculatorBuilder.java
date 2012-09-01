
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
public class ImplCalculatorBuilder
    implements CalculatorBuilder, BuilderImplementation
{

    private final CalculatorHelper _helper;
    private final Object _returnValue;

    ImplCalculatorBuilder(CalculatorHelper helper, Object returnValue) {
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
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        ObjectWrapper<CalculationHelper> helper1 = new ObjectWrapper<CalculationHelper>();
        _helper.$(startingValue, helper1);
        ImplCalculationBuilder step1 = new ImplCalculationBuilder(helper1 .get(), null);
         
        return step1;
    }

}
