
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza;
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
 * Generated using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.pizza.builder.Pizza.PizzaHelper
 */
@Generated(value = "unquietcode.tools.flapi", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface PizzaBuilder_2addTopping_32 <_ReturnType> {
    @MethodInfo(type = TransitionType.Lateral)
    PizzaBuilder_2addTopping<_ReturnType> addTopping(Topping topping);

    @MethodInfo(type = TransitionType.Terminal)
    Pizza bake();
}
