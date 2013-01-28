
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
 * Generated on January 27, 2013 22:16:43 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 27, 2013 22:16:43 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 <_ReturnType >{


    BlockChainBuilder_m11_m20_m10 <MethodBuilder_m12_m13_m14_m15_m16_m17_m18 <_ReturnType>> addBlockChain();

    _ReturnType any();

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num);

    _ReturnType between(int atLeast, int atMost);

    _ReturnType exactly(int num);

    _ReturnType last();

    _ReturnType last(Class returnType);

}
