
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
 * Generated on May 28, 2012 17:01:37 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 17:01:37 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodHelper {


    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void between(int atLeast, int atMost);

    void any();

    void atMost(int num);

    void exactly(int num);

    void atLeast(int num);

    void last(Class returnType);

    void last();

}
