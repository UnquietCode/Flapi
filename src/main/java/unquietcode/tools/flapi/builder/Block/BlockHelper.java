
package unquietcode.tools.flapi.builder.Block;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 04, 2013 10:10:17 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 04, 2013 10:10:17 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockHelper {


    /**
     * add a new method which proceeds to an existing block
     * 
     */
    void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    /**
     * add a new method to the block
     * 
     */
    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    /**
     * finish editing of the current block
     * 
     */
    void endBlock();

    /**
     * Start a new block, nested inside the current one.
     * The block can be referenced from outside by using the designated name.
     * 
     */
    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    /**
     * Start a new anonymous block, nested inside the current one.
     * The block cannot be referenced from outside, as it has no name.
     * 
     */
    void startBlock(String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

}
