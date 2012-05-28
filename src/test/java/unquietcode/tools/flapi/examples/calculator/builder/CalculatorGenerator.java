
package unquietcode.tools.flapi.examples.calculator.builder;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 10:12:35 CDT using version 0.2
 * 
 */
public class CalculatorGenerator {


    @SuppressWarnings("unchecked")
    public static CalculatorBuilder<ObjectWrapper> begin(CalculatorHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new ImplCalculatorBuilder(helper, helper._getReturnValue());
    }

}
