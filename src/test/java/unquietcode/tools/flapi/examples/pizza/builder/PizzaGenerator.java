
package unquietcode.tools.flapi.examples.pizza.builder;



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
public class PizzaGenerator {


    @SuppressWarnings("unchecked")
    public static PizzaBuilder_addCheese_addSauce_addToppingi3 <unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza> makePizza(PizzaHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplPizzaBuilder_addCheese_addSauce_addToppingi3(helper, helper._getReturnValue());
    }

}
