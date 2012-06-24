
package unquietcode.tools.flapi.examples.pizza.builder;

import javax.annotation.Generated;


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
public class PizzaGenerator {


    @SuppressWarnings("unchecked")
    public static PizzaBuilder_addCheese_addSauce_addToppingi3 <Void> makePizza(PizzaHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplPizzaBuilder_addCheese_addSauce_addToppingi3(helper, null);
    }

}
