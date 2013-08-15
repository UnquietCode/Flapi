
package unquietcode.tools.flapi.builder.Method;

import unquietcode.tools.flapi.builder.BlockChain.BlockChainHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;

import javax.annotation.Generated;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 14, 2013 21:46:20 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 14, 2013 21:46:20 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodHelper {
    /**
     * Add a BlockChain, which is a sequence of blocks  which must bepassed through
     *  before the method returns.
     */
    void addBlockChain(AtomicReference<BlockChainHelper> _helper1);

    /**
     * expose the method only after the specified group is finished
     */
    void after(int group);

    /**
     * expect the method [0, inf) times
     */
    void any();

    /**
     * expect the method [X, inf) times
     */
    void atLeast(int num);

    /**
     * expect the method [0, X] times
     */
    void atMost(int num);

    /**
     * expect the method [0, X] times, and assign a group number
     */
    void atMost(int num, int group);

    /**
     * expect the method [atLeast, atMost] times
     */
    void between(int atLeast, int atMost);

    /**
     * expect the method [X, X] times
     */
    void exactly(int num);

    /**
     * mark the method as terminal, exiting the block when called
     */
    void last();

    /**
     * mark the method as terminal, returning an object of the given type when called
     */
    void last(Class returnType);

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     */
    void markAsDeprecated(String reason);

    /**
     * Add javadoc style documentation to the method.
     */
    void withDocumentation(AtomicReference<DocumentationHelper> _helper1);

    /**
     * Add javadoc style documentation to the method.
     */
    void withDocumentation(String documentation);
}
