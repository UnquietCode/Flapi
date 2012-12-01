
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
 * Generated on December 01, 2012 13:14:02 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "December 01, 2012 13:14:02 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10 <_ReturnType >{


    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m1_m2_m20_m9_m10 <DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m1_m2_m20_m9_m10 <DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m8_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_m1_m2_m3_m4_m6_m7_m8_m9_m10 <_ReturnType> setDescriptorName(String descriptorName);

    Descriptor build();

    DescriptorBuilder_m1_m2_m3_m4_m5_m7_m8_m9_m10 <_ReturnType> setPackage(String packageName);

    DescriptorBuilder_m1_m2_m3_m5_m6_m7_m8_m9_m10 <_ReturnType> enableCondensedClassNames();

    DescriptorBuilder_m1_m2_m3_m4_m5_m6_m8_m9_m10 <_ReturnType> setReturnType(Class returnType);

    DescriptorBuilder_m1_m2_m3_m4_m5_m6_m7_m9_m10 <_ReturnType> setStartingMethodName(String methodName);

}
