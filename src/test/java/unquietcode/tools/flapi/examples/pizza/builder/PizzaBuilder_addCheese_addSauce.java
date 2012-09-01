
package unquietcode.tools.flapi.examples.pizza.builder;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType;

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
public interface PizzaBuilder_addCheese_addSauce<_ReturnType >{


    PizzaBuilder_addSauce<_ReturnType> addCheese();

    unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza bake();

    PizzaBuilder_addCheese<_ReturnType> addSauce(SauceType sauceType);

}
