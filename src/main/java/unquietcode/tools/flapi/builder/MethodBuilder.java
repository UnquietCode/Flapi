
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on September 01, 2012 18:27:59 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "September 01, 2012 18:27:59 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder<_ReturnType >{


    _ReturnType atLeast(int num);

    _ReturnType last(Class returnType);

    _ReturnType exactly(int num);

    _ReturnType last();

    _ReturnType any();

    _ReturnType between(int atLeast, int atMost);

    _ReturnType atMost(int num);

}
