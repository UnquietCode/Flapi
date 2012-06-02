
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
 * Generated on June 01, 2012 21:44:52 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 01, 2012 21:44:52 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_addBlockChain<_ParentType >{


    _ParentType any();

    _ParentType atLeast(int num);

    _ParentType atMost(int num);

    _ParentType between(int atLeast, int atMost);

    _ParentType exactly(int num);

    _ParentType last();

    _ParentType last(Class returnType);

    BlockChainBuilder_addBlockChain<MethodBuilder<_ParentType>> addBlockChain();

}
