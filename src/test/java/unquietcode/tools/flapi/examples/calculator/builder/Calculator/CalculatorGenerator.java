package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.examples.calculator.Calculator;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder.Start;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;
import unquietcode.tools.flapi.runtime.Supplier;

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
public class CalculatorGenerator {
    public static Start<Void> begin(Calculator helper, ExecutionListener... listeners) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
        handler.addListeners(listeners);
        return handler._proxy(Start.class);
    }

    public static CalculatorFactory factory(final Supplier<Calculator> provider, final ExecutionListener... listeners) {
        return new CalculatorFactory() {
            public Start<Void> begin() {
                Calculator helper = provider.get();
                return CalculatorGenerator.begin(helper, listeners);
            }
        };
    }
}
