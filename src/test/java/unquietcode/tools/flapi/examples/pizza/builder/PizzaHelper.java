
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
 * Generated on May 09, 2012 23:31:29 CDT using version 0.1
 * 
 */
public interface PizzaHelper {


    unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza _getReturnValue();

    void bake();

    void addSauce(SauceType sauceType);

    void addTopping(Topping topping);

    void addCheese();

}
