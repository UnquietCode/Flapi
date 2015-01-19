
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.Supplier;
import unquietcode.tools.flapi.examples.pizza.builder.Pizza.PizzaBuilder.Start;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;

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
 */
@Generated(value = "unquietcode.tools.flapi", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public class PizzaGenerator {
    public static Start<Void> makePizza(PizzaHelper helper, ExecutionListener... listeners) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
        handler.addListeners(listeners);
        return handler._proxy(Start.class);
    }

    public static PizzaFactory factory(final Supplier<PizzaHelper> provider, final ExecutionListener... listeners) {
        return new PizzaFactory() {
            public Start<Void> makePizza() {
                PizzaHelper helper = provider.get();
                return PizzaGenerator.makePizza(helper, listeners);
            }
        };
    }
}
