
package unquietcode.tools.flapi.builder.Descriptor;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m25_m9_m10;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22;


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
public interface DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType >{


    /**
     * add a new method which proceeds to an existing block
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     * 
     */
    Descriptor build();

    /**
     * set the name of the top level descriptor
     * 
     */
    DescriptorBuilder_m1_m2_m3_m9_m10 <_ReturnType> setDescriptorName(String descriptorName);

    /**
     * Starts a new block.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Starts a new block.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
