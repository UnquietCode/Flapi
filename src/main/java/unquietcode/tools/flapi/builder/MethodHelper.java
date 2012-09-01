
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
 * Generated on September 01, 2012 18:27:59 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "September 01, 2012 18:27:59 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodHelper {


    void last(Class returnType);

    void any();

    void last();

    void atMost(int num);

    void exactly(int num);

    void atLeast(int num);

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void between(int atLeast, int atMost);

}
