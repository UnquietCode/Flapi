
package unquietcode.tools.flapi.examples.pizza.builder;

import unquietcode.tools.flapi.DescriptorBuilderException;


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
public class PizzaGenerator {


    @SuppressWarnings("unchecked")
    public static PizzaBuilder_addCheese_addSauce_addTopping$3 <unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza> makePizza(PizzaHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplPizzaBuilder_addCheese_addSauce_addTopping$3(helper, helper._getReturnValue());
    }

}
