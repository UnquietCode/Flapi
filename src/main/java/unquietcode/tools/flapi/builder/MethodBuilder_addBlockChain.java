
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:25:34 CDT using version 0.1
 * 
 */
public interface MethodBuilder_addBlockChain<_ReturnType >{


    _ReturnType any();

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num);

    _ReturnType between(int atLeast, int atMost);

    _ReturnType last();

    _ReturnType once();

    BlockChainBuilder_addBlockChain<MethodBuilder<_ReturnType>> addBlockChain();

}
