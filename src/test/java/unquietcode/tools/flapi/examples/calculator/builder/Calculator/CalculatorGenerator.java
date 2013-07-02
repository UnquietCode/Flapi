
package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder.$;
import unquietcode.tools.flapi.support.BlockInvocationHandler;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 21:53:49 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 21:53:49 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class CalculatorGenerator {
    @SuppressWarnings("unchecked")
    public static $<Void> begin(CalculatorHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new BlockInvocationHandler(helper, null)._proxy($.class);
    }
}
