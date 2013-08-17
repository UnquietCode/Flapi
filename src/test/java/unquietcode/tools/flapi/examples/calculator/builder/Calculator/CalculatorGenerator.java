
package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder.$;
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
 * Generated on August 17, 2013 14:55:04 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 17, 2013 14:55:04 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class CalculatorGenerator {
    public static $<Void> begin(CalculatorHelper helper, ExecutionListener... listeners) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
        handler.addListeners(listeners);
        return handler._proxy($.class);
    }
}
