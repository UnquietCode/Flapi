
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on January 27, 2013 22:16:43 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 27, 2013 22:16:43 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m1_m2_m3_m5_m7_m9_m10 <_ReturnType >{


    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19 <DescriptorBuilder_m1_m2_m3_m5_m7_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19 <DescriptorBuilder_m1_m2_m3_m5_m7_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    Descriptor build();

    DescriptorBuilder_m1_m2_m3_m7_m9_m10 <_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_m1_m2_m3_m5_m9_m10 <_ReturnType> setReturnType(Class returnType);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19 <BlockBuilder_m1_m2_m21_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m7_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19 <BlockBuilder_m1_m2_m21_m9_m10 <DescriptorBuilder_m1_m2_m3_m5_m7_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
