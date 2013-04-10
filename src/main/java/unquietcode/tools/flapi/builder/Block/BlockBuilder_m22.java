
package unquietcode.tools.flapi.builder.Block;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 09, 2013 20:26:31 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 09, 2013 20:26:31 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockBuilder_m22 <_ReturnType> {
    /**
     * add a new method which proceeds to an existing block
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * add a new method to the block
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <_ReturnType>> addMethod(String methodSignature);

    /**
     * finish editing of the current block
     */
    _ReturnType endBlock();

    /**
     * Start a new block, nested inside the current one.
     * The block can be referenced from outside by using the designated name.
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <BlockBuilder_m22 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Start a new anonymous block, nested inside the current one.
     * The block cannot be referenced from outside, as it has no name.
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <BlockBuilder_m22 <_ReturnType>>> startBlock(String methodSignature);
}
