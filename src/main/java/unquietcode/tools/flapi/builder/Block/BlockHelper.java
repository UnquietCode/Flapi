
package unquietcode.tools.flapi.builder.Block;

import unquietcode.tools.flapi.builder.Method.MethodHelper;

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
public interface BlockHelper {
    /**
     * add a new method which proceeds to an existing block
     */
    void addBlockReference(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * Adds an enum selector, by passing in an enum class.
     */
    void addEnumSelector(Class clazz, String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * add a new method to the block
     */
    void addMethod(String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * finish editing of the current block
     */
    void endBlock();

    /**
     * Start a new block, nested inside the current one.
     * The block can be referenced from outside by using the designated name.
     */
    void startBlock(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2);

    /**
     * Start a new anonymous block, nested inside the current one.
     * The block cannot be referenced from outside, as it has no name.
     */
    void startBlock(String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2);
}
