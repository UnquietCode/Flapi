
package unquietcode.tools.flapi.builder.Descriptor;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockBuilder_m22;
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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorBuilder_m3_m5 <_ReturnType> {
    /**
     * add a new method which proceeds to an existing block
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m3_m5 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * Add a new method to the top level descriptor block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <DescriptorBuilder_m3_m5 <_ReturnType>> addMethod(String methodSignature);

    /**
     * Finish work and build the descriptor. This should only be called once.
     */
    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    Descriptor build();

    /**
     * set the root package name to use for the generated classes
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    @Tracked(atLeast = 1, key = "Descriptor_setPackage$String_packageName")
    DescriptorBuilder_m5 <_ReturnType> setPackage(String packageName);

    /**
     * set the name of the generator's starting method (default is 'create')
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    DescriptorBuilder_m3 <_ReturnType> setStartingMethodName(String methodName);

    /**
     * Starts a new block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m22 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder_m3_m5 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Starts a new block.
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {
        MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 .class,
        BlockBuilder_m22 .class
    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16_m17_m18 <BlockBuilder_m22 <DescriptorBuilder_m3_m5 <_ReturnType>>> startBlock(String methodSignature);
}
