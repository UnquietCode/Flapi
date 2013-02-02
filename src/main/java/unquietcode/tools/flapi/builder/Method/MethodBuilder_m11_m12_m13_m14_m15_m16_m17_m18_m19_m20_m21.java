
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.BlockChain.BlockChainBuilder_m11_m24_m25_m10;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 02, 2013 12:47:50 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 12:47:50 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <_ReturnType >{


    BlockChainBuilder_m11_m24_m25_m10 <MethodBuilder_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <_ReturnType>> addBlockChain();

    _ReturnType any();

    _ReturnType any(int group);

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num);

    _ReturnType atMost(int num, int group);

    _ReturnType between(int atLeast, int atMost);

    _ReturnType exactly(int num);

    _ReturnType last();

    _ReturnType last(Class returnType);

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20 <_ReturnType> markAsDeprecated(String reason);

}
