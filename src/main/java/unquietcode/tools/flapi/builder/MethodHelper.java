
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 24, 2012 14:26:52 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 24, 2012 14:26:52 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodHelper {


    void any();

    void between(int atLeast, int atMost);

    void atLeast(int num);

    void last();

    void atMost(int num);

    void exactly(int num);

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void last(Class returnType);

}
