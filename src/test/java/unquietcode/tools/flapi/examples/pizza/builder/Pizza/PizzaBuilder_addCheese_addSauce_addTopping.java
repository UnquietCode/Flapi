
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping;

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
public interface PizzaBuilder_addCheese_addSauce_addTopping<_ReturnType> {
    PizzaBuilder_addSauce_addTopping<_ReturnType> addCheese();

    PizzaBuilder_addCheese_addTopping<_ReturnType> addSauce(SauceType sauceType);

    PizzaBuilder_addCheese_addSauce<_ReturnType> addTopping(Topping topping);

    Pizza bake();
}
