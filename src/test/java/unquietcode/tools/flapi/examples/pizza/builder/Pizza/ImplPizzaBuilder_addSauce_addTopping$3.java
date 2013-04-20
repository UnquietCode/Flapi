
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;
import unquietcode.tools.flapi.support.BuilderImplementation;

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
public class ImplPizzaBuilder_addSauce_addTopping$3
    implements PizzaBuilder_addSauce_addTopping$3 <Object> , BuilderImplementation
{
    private final PizzaHelper _helper;
    private final Object _returnValue;

    public ImplPizzaBuilder_addSauce_addTopping$3(PizzaHelper helper, Object returnValue) {
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

    public PizzaBuilder_addTopping$3 addSauce(SauceType sauceType) {
        _helper.addSauce(sauceType);
        ImplPizzaBuilder_addTopping$3 step1 = new ImplPizzaBuilder_addTopping$3(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public PizzaBuilder_addSauce_addTopping$2 addTopping(Topping topping) {
        _helper.addTopping(topping);
        ImplPizzaBuilder_addSauce_addTopping$2 step1 = new ImplPizzaBuilder_addSauce_addTopping$2(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public Pizza bake() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Pizza intermediateResult = _helper.bake();
         
        return intermediateResult;
    }
}
