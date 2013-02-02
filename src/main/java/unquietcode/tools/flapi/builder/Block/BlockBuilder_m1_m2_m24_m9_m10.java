
package unquietcode.tools.flapi.builder.Block;

import javax.annotation.Generated;
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
public interface BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType >{


    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    _ReturnType endBlock();

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 <BlockBuilder_m1_m2_m24_m9_m10 <BlockBuilder_m1_m2_m24_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
