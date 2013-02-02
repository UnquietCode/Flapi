
package unquietcode.tools.flapi.builder.Descriptor;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m24_m9_m10;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 02, 2013 12:18:51 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 12:18:51 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType >{


    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     * 
     */
    Descriptor build();

    /**
     * set the name of the top level descriptor
     * 
     */
    DescriptorBuilder_m1_m2_m3_m6_m7_m8_m9_m10 <_ReturnType> setDescriptorName(String descriptorName);

    /**
     * set the root package name to use for the generated classes
     * 
     */
    DescriptorBuilder_m1_m2_m3_m5_m7_m8_m9_m10 <_ReturnType> setPackage(String packageName);

    /**
     * set the return type for the top level descriptor (default is void)
     * 
     */
    DescriptorBuilder_m1_m2_m3_m5_m6_m8_m9_m10 <_ReturnType> setReturnType(Class returnType);

    /**
     * set the name of the generator's starting method (default is 'create')
     * 
     */
    DescriptorBuilder_m1_m2_m3_m5_m6_m7_m9_m10 <_ReturnType> setStartingMethodName(String methodName);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
