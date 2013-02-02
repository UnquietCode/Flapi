
package unquietcode.tools.flapi.builder.Descriptor;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.builder.Block.ImplBlockBuilder_m1_m2_m26_m9_m10;
import unquietcode.tools.flapi.builder.Method.ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 02, 2013 12:47:50 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 02, 2013 12:47:50 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDescriptorBuilder_m1_m2_m3_m9_m10
    implements DescriptorBuilder_m1_m2_m3_m9_m10, BuilderImplementation
{

    private final DescriptorHelper _helper;
    private final Object _returnValue;

    public ImplDescriptorBuilder_m1_m2_m3_m9_m10(DescriptorHelper helper, Object returnValue) {
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
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23(helper1 .get(), this);
         
        return step1;
    }

    /**
     * Add a new method to the top level descriptor block.
     * 
     */
    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23(helper1 .get(), this);
         
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

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m26_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m26_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23(helper1 .get(), step1);
         
        return step2;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 startBlock(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m26_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m26_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22_m23(helper1 .get(), step1);
         
        return step2;
    }

}
