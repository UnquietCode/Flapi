
package unquietcode.tools.flapi.builder.Descriptor;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;
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
public interface DescriptorBuilder<_ReturnType> {
    /**
     * add a new method which proceeds to an existing block
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder<_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder<_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     */
    Descriptor build();

    /**
     * Starts a new block.
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder<_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Starts a new block.
     */
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder<_ReturnType>>> startBlock(String methodSignature);

    public interface $<_ReturnType>
        extends DescriptorBuilder_m1_m2_m3_m4_m5 <_ReturnType>
    {

    }
}
