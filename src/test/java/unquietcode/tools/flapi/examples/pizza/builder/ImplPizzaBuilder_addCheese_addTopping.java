
package unquietcode.tools.flapi.examples.pizza.builder;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:31:29 CDT using version 0.1
 * 
 */
public class ImplPizzaBuilder_addCheese_addTopping
    implements PizzaBuilder_addCheese_addTopping
{

    private final PizzaHelper _helper;
    private final Object _returnValue;

    ImplPizzaBuilder_addCheese_addTopping(PizzaHelper helper, Object returnValue) {
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

    public PizzaBuilder_addTopping addCheese() {
        _helper.addCheese();
         
        PizzaBuilder_addTopping retval = new ImplPizzaBuilder_addTopping(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

    public PizzaBuilder_addCheese addTopping(Topping topping) {
        _helper.addTopping(topping);
         
        PizzaBuilder_addCheese retval = new ImplPizzaBuilder_addCheese(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
