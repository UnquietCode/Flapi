
package unquietcode.tools.flapi.examples.pizza.builder;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:15:25 CDT using version 0.2
 * 
 */
public class ImplPizzaBuilder_addSauce_addTopping
    implements PizzaBuilder_addSauce_addTopping
{

    private final PizzaHelper _helper;
    private final Object _returnValue;

    ImplPizzaBuilder_addSauce_addTopping(PizzaHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public Object bake() {
        _checkInvocations();
        _helper.bake();
         
        Object retval = _returnValue;
        return retval;
    }

    public PizzaBuilder_addTopping addSauce(SauceType sauceType) {
        _helper.addSauce(sauceType);
         
        PizzaBuilder_addTopping retval = new ImplPizzaBuilder_addTopping(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

    public PizzaBuilder_addSauce addTopping(Topping topping) {
        _helper.addTopping(topping);
         
        PizzaBuilder_addSauce retval = new ImplPizzaBuilder_addSauce(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
