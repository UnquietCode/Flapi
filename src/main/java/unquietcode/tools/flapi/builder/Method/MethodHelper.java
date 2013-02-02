
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.BlockChain.BlockChainHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public interface MethodHelper {


    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void any();

    void any(int group);

    void atLeast(int num);

    void atMost(int num);

    void atMost(int num, int group);

    void between(int atLeast, int atMost);

    void exactly(int num);

    void last();

    void last(Class returnType);

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     * 
     */
    void markAsDeprecated(String reason);

    /**
     * 
     * 
     */
    void withDocumentation(ObjectWrapper<DocumentationHelper> _helper1);

    void withDocumentation(String documentation);

}
