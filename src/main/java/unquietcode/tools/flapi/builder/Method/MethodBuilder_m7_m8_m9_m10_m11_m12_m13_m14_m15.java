
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 09, 2013 20:26:31 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 09, 2013 20:26:31 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_m7_m8_m9_m10_m11_m12_m13_m14_m15 <_ReturnType> {
    /**
     * expose the method only after the specified group is finished
     */
    MethodBuilder_m8_m9_m10_m11_m12_m13_m14_m15 <_ReturnType> after(int group);

    /**
     * expect the method [0, inf) times
     */
    _ReturnType any();

    /**
     * expect the method [X, inf) times
     */
    _ReturnType atLeast(int num);

    /**
     * expect the method [0, X] times
     */
    _ReturnType atMost(int num);

    /**
     * expect the method [0, X] times, and assign a group number
     */
    _ReturnType atMost(int num, int group);

    /**
     * expect the method [atLeast, atMost] times
     */
    _ReturnType between(int atLeast, int atMost);

    /**
     * expect the method [X, X] times
     */
    _ReturnType exactly(int num);

    /**
     * mark the method as terminal, exiting the block when called
     */
    _ReturnType last();

    /**
     * mark the method as terminal, returning an object of the given type when called
     */
    _ReturnType last(Class returnType);
}
