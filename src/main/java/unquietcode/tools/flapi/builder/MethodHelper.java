
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 12, 2012 24:38:04 CDT using version 0.1
 * 
 */
public interface MethodHelper {


    void atMost(int num);

    void once();

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void any();

    void between(int atLeast, int atMost);

    void last();

    void atLeast(int num);

}
