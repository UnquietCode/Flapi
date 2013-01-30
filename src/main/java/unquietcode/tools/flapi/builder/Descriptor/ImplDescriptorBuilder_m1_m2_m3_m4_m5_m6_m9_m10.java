
package unquietcode.tools.flapi.builder.Descriptor;

import java.lang.reflect.Field;
import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Block.ImplBlockBuilder_m1_m2_m24_m9_m10;
import unquietcode.tools.flapi.builder.Method.ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ExpectedInvocationsException;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on January 30, 2013 1:01:45 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 30, 2013 1:01:45 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDescriptorBuilder_m1_m2_m3_m4_m5_m6_m9_m10
    implements DescriptorBuilder_m1_m2_m3_m4_m5_m6_m9_m10, BuilderImplementation
{

    private final DescriptorHelper _helper;
    private final Object _returnValue;
    int ic_Descriptor_setDescriptorName$String_descriptorName = 1;
    int ic_Descriptor_setPackage$String_packageName = 1;

    public ImplDescriptorBuilder_m1_m2_m3_m4_m5_m6_m9_m10(DescriptorHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        Class clazz = next.getClass();
         
        try {
            Field field = clazz.getDeclaredField("ic_Descriptor_setDescriptorName$String_descriptorName");
            field.setInt(next, ic_Descriptor_setDescriptorName$String_descriptorName);
        } catch (Exception _x) {
            // nothing
        }
         
        try {
            Field field = clazz.getDeclaredField("ic_Descriptor_setPackage$String_packageName");
            field.setInt(next, ic_Descriptor_setPackage$String_packageName);
        } catch (Exception _x) {
            // nothing
        }
    }

    public void _checkInvocations() {
        if (ic_Descriptor_setDescriptorName$String_descriptorName > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'setDescriptorName(String descriptorName)'.");
        }
        if (ic_Descriptor_setPackage$String_packageName > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'setPackage(String packageName)'.");
        }
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Add a new method to the top level descriptor block.
     * 
     */
    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Finish work and build the descriptor. This should only be called once.
     * 
     */
    public Descriptor build() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Descriptor intermediateResult = _helper.build();
         
        return intermediateResult;
    }

    /**
     * Allow class names to be condensed, at the cost of no longer being
     * humanly readable. If your generated class names are too long to be
     * compiled, you will have to use this.
     * 
     */
    public DescriptorBuilder_m1_m2_m3_m5_m6_m9_m10 enableCondensedClassNames() {
        _helper.enableCondensedClassNames();
        ImplDescriptorBuilder_m1_m2_m3_m5_m6_m9_m10 step1 = new ImplDescriptorBuilder_m1_m2_m3_m5_m6_m9_m10(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * set the name of the top level descriptor
     * 
     */
    public DescriptorBuilder_m1_m2_m3_m4_m6_m9_m10 setDescriptorName(String descriptorName) {
        --ic_Descriptor_setDescriptorName$String_descriptorName;
        _helper.setDescriptorName(descriptorName);
        ImplDescriptorBuilder_m1_m2_m3_m4_m6_m9_m10 step1 = new ImplDescriptorBuilder_m1_m2_m3_m4_m6_m9_m10(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    /**
     * set the root package name to use for the generated classes
     * 
     */
    public DescriptorBuilder_m1_m2_m3_m4_m5_m9_m10 setPackage(String packageName) {
        --ic_Descriptor_setPackage$String_packageName;
        _helper.setPackage(packageName);
        ImplDescriptorBuilder_m1_m2_m3_m4_m5_m9_m10 step1 = new ImplDescriptorBuilder_m1_m2_m3_m4_m5_m9_m10(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m24_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m24_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21(helper1 .get(), step1);
         
        return step2;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 startBlock(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m24_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m24_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21(helper1 .get(), step1);
         
        return step2;
    }

}
