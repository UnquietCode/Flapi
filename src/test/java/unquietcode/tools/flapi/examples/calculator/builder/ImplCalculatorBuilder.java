
package unquietcode.tools.flapi.examples.calculator.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplCalculatorBuilder
    implements CalculatorBuilder, BuilderImplementation
{

    private final CalculatorHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplCalculatorBuilder(CalculatorHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public CalculationBuilder $(int startingValue) {
        ObjectWrapper<CalculationHelper> helper1 = new ObjectWrapper<CalculationHelper>();
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.$(startingValue, helper1);
         
        ImplCalculationBuilder step1 = new ImplCalculationBuilder(helper1 .get(), _returnValue);
        return step1;
    }

}
