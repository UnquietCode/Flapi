
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 12:09:05 CDT using version 0.2
 * 
 */
public interface MethodHelper {


    void between(int atLeast, int atMost);

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void atMost(int num);

    void last();

    void any();

    void atLeast(int num);

    void exactly(int num);

}
