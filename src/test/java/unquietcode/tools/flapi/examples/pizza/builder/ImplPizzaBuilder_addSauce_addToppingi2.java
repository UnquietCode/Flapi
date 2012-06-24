
package unquietcode.tools.flapi.examples.pizza.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;


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
public class ImplPizzaBuilder_addSauce_addToppingi2
    implements PizzaBuilder_addSauce_addToppingi2, BuilderImplementation
{

    private final PizzaHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplPizzaBuilder_addSauce_addToppingi2(PizzaHelper helper, BuilderImplementation returnValue) {
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

    public unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza bake() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza intermediateResult = _helper.bake();
         
        return intermediateResult;
    }

    public PizzaBuilder_addToppingi2 addSauce(SauceType sauceType) {
        _helper.addSauce(sauceType);
         
        PizzaBuilder_addToppingi2 retval = new ImplPizzaBuilder_addToppingi2(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

    public PizzaBuilder_addSauce_addTopping addTopping(Topping topping) {
        _helper.addTopping(topping);
         
        PizzaBuilder_addSauce_addTopping retval = new ImplPizzaBuilder_addSauce_addTopping(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
