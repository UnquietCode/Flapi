
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
 * Generated on May 28, 2012 10:15:25 CDT using version 0.2
 * 
 */
public interface PizzaBuilder_addCheese_addTopping<_ReturnType >{


    _ReturnType bake();

    PizzaBuilder_addTopping<_ReturnType> addCheese();

    PizzaBuilder_addCheese<_ReturnType> addTopping(Topping topping);

}
