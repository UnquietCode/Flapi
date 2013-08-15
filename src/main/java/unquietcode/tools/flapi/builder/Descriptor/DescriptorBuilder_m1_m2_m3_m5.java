
package unquietcode.tools.flapi.builder.Descriptor;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m20;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.Tracked;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


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
public interface DescriptorBuilder_m1_m2_m3_m5 <_ReturnType> {
    /**
     * add a new method which proceeds to an existing block
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m1_m2_m3_m5 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Adds an enum selector, by passing in an enum class.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m1_m2_m3_m5 <_ReturnType>> addEnumSelector(Class clazz, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m1_m2_m3_m5 <_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     */
    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    Descriptor build();

    /**
     * Allow class names to be condensed, at the cost of no longer being
     * humanly readable. If your generated class names are too long to be
     * compiled, you will have to use this.
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    DescriptorBuilder_m2_m3_m5 <_ReturnType> enableCondensedClassNames();

    /**
     * set the name of the top level descriptor
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    @Tracked(atLeast = 1, key = "Descriptor_setDescriptorName$String_descriptorName")
    DescriptorBuilder_m1_m3_m5 <_ReturnType> setDescriptorName(String descriptorName);

    /**
     * set the root package name to use for the generated classes
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    @Tracked(atLeast = 1, key = "Descriptor_setPackage$String_packageName")
    DescriptorBuilder_m1_m2_m5 <_ReturnType> setPackage(String packageName);

    /**
     * set the name of the generator's starting method (default is 'create')
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    DescriptorBuilder_m1_m2_m3 <_ReturnType> setStartingMethodName(String methodName);

    /**
     * Starts a new block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m20 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m20 <DescriptorBuilder_m1_m2_m3_m5 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Starts a new block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m20 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m20 <DescriptorBuilder_m1_m2_m3_m5 <_ReturnType>>> startBlock(String methodSignature);
}
