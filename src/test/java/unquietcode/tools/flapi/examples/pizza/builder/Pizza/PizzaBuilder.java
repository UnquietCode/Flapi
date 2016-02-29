
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Pizza;
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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface PizzaBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Terminal)
    Pizza bake();


    /**
     * Marker interface denoting the main entry point for this block.
     */
    public interface Head<_ReturnType>
        extends PizzaBuilder_2addCheese_2addSauce_2addTopping_33 <_ReturnType>
    {

    }


    /**
     * Marker interface denoting the main entry point for this descriptor.
     */
    public interface Start
        extends PizzaBuilder.Head<Void>
    {

    }
}
