
package unquietcode.tools.flapi.builder.Descriptor;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
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
public interface DescriptorHelper {
    /**
     * add a new method which proceeds to an existing block
     */
    void addBlockReference(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * Adds an enum selector, by passing in an enum class.
     */
    void addEnumSelector(Class clazz, String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * Add a new method to the top level descriptor block.
     */
    void addMethod(String methodSignature, AtomicReference<MethodHelper> _helper1);

    /**
     * Finish work and build the descriptor. This should only be called once.
     */
    Descriptor build();

    /**
     * Allow class names to be condensed, at the cost of no longer being
     * humanly readable. If your generated class names are too long to be
     * compiled, you will have to use this.
     */
    void enableCondensedClassNames();

    /**
     * set the name of the top level descriptor
     */
    void setDescriptorName(String descriptorName);

    /**
     * set the root package name to use for the generated classes
     */
    void setPackage(String packageName);

    /**
     * set the return type for the top level descriptor (default is void)
     */
    void setReturnType(Class returnType);

    /**
     * set the name of the generator's starting method (default is 'create')
     */
    void setStartingMethodName(String methodName);

    /**
     * Starts a new block.
     */
    void startBlock(String blockName, String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2);

    /**
     * Starts a new block.
     */
    void startBlock(String methodSignature, AtomicReference<MethodHelper> _helper1, AtomicReference<BlockHelper> _helper2);
}
