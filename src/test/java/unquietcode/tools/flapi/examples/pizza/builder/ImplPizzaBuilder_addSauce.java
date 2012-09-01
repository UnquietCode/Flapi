
package unquietcode.tools.flapi.examples.pizza.builder;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;

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
public class ImplPizzaBuilder_addSauce
    implements PizzaBuilder_addSauce, BuilderImplementation
{

    private final PizzaHelper _helper;
    private final Object _returnValue;

    ImplPizzaBuilder_addSauce(PizzaHelper helper, Object returnValue) {
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

    public unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza bake() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza intermediateResult = _helper.bake();
         
        return intermediateResult;
    }

    public PizzaBuilder addSauce(SauceType sauceType) {
        _helper.addSauce(sauceType);
        ImplPizzaBuilder step1 = new ImplPizzaBuilder(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
