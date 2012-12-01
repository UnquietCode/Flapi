
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 25, 2012 17:55:21 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 25, 2012 17:55:21 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodHelper {


    void between(int atLeast, int atMost);

    void atMost(int num);

    void last();

    void last(Class returnType);

    void exactly(int num);

    void atLeast(int num);

    void any();

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

}
