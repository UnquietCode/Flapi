
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Documentation.DocumentationBuilder_m25_m26;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 02, 2013 12:18:51 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 12:18:51 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m20 <_ReturnType >{


    _ReturnType any(int... groups);

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num, int... groups);

    _ReturnType between(int atLeast, int atMost);

    _ReturnType exactly(int num);

    _ReturnType last();

    _ReturnType last(Class returnType);

    /**
     * Add javadoc style documentation to the method.
     * 
     */
    DocumentationBuilder_m25_m26 <MethodBuilder_m12_m13_m14_m15_m16_m17_m18 <_ReturnType>> withDocumentation();

}
