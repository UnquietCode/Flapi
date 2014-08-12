
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
 * Generated on August 12, 2014 13:17:30 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface PizzaBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Terminal)
    Pizza bake();

    public interface Start<_ReturnType>
        extends PizzaBuilder_2addCheese_4f_2addSauce_4f_2addTopping_33_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Lateral)
        PizzaBuilder_2addSauce_4f_2addTopping_33_4f<_ReturnType> addCheese();

        @MethodInfo(type = TransitionType.Lateral)
        PizzaBuilder_2addCheese_4f_2addTopping_33_4f<_ReturnType> addSauce(SauceType sauceType);

        @MethodInfo(type = TransitionType.Lateral)
        PizzaBuilder_2addCheese_4f_2addSauce_4f_2addTopping_32_4f<_ReturnType> addTopping(Topping topping);

        @MethodInfo(type = TransitionType.Terminal)
        Pizza bake();
    }
}
