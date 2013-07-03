
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface PizzaBuilder_addSauce_addTopping$2 <_ReturnType> {
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    PizzaBuilder_addTopping$2 <_ReturnType> addSauce(SauceType sauceType);

    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    PizzaBuilder_addSauce_addTopping<_ReturnType> addTopping(Topping topping);

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    Pizza bake();
}
