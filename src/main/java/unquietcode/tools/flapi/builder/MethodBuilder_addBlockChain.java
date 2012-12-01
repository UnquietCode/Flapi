
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
 * Generated on November 25, 2012 17:55:21 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 25, 2012 17:55:21 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_addBlockChain<_ReturnType >{


    _ReturnType between(int atLeast, int atMost);

    _ReturnType atMost(int num);

    _ReturnType last();

    _ReturnType last(Class returnType);

    _ReturnType exactly(int num);

    _ReturnType atLeast(int num);

    _ReturnType any();

    BlockChainBuilder_addBlockChain<MethodBuilder<_ReturnType>> addBlockChain();

}
