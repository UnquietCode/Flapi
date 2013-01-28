
package unquietcode.tools.flapi.builder.Descriptor;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m1_m2_m22_m9_m10;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on January 29, 2013 21:38:13 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 29, 2013 21:38:13 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m1_m2_m3_m5_m8_m9_m10 <_ReturnType >{


    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20 <DescriptorBuilder_m1_m2_m3_m5_m8_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20 <DescriptorBuilder_m1_m2_m3_m5_m8_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    Descriptor build();

    DescriptorBuilder_m1_m2_m3_m8_m9_m10 <_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType> setStartingMethodName(String methodName);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20 <BlockBuilder_m1_m2_m22_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m8_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20 <BlockBuilder_m1_m2_m22_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m8_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
