
package unquietcode.tools.flapi.builder.Descriptor;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18;
import unquietcode.tools.flapi.support.MethodInfo;
import unquietcode.tools.flapi.support.Tracked;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 20:13:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 20:13:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m2_m5 <_ReturnType> {
    /**
     * add a new method which proceeds to an existing block
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m2_m5 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m2_m5 <_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     */
    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = 2, chain = {

    })
    Descriptor build();

    /**
     * set the name of the top level descriptor
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 1, chain = {

    })
    @Tracked(atLeast = 1, key = "Descriptor_setDescriptorName$String_descriptorName")
    DescriptorBuilder_m5 <_ReturnType> setDescriptorName(String descriptorName);

    /**
     * set the name of the generator's starting method (default is 'create')
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 1, chain = {

    })
    DescriptorBuilder_m2 <_ReturnType> setStartingMethodName(String methodName);

    /**
     * Starts a new block.
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m22 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder_m2_m5 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Starts a new block.
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m22 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder_m2_m5 <_ReturnType>>> startBlock(String methodSignature);
}
