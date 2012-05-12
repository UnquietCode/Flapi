
package unquietcode.tools.flapi.examples.calculator.builder;

import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 12, 2012 24:56:40 CDT using version 0.1
 * 
 */
public class CalculatorGenerator {


    @SuppressWarnings("unchecked")
    public static CalculatorBuilder<ObjectWrapper> begin(CalculatorHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplCalculatorBuilder(helper, helper._getReturnValue());
    }

}
