
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public class ImplDescriptorBuilder_m1_m2_m3_m7_m9_m10
    implements DescriptorBuilder_m1_m2_m3_m7_m9_m10, BuilderImplementation
{

    private final DescriptorHelper _helper;
    private final Object _returnValue;

    ImplDescriptorBuilder_m1_m2_m3_m7_m9_m10(DescriptorHelper helper, Object returnValue) {
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

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18(helper1 .get(), this);
         
        return step1;
    }

    public DescriptorBuilder_m1_m2_m3_m9_m10 setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
        ImplDescriptorBuilder_m1_m2_m3_m9_m10 step1 = new ImplDescriptorBuilder_m1_m2_m3_m9_m10(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 startBlock(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m20_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m20_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18(helper1 .get(), step1);
         
        return step2;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 step1 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18(helper1 .get(), this);
         
        return step1;
    }

    public Descriptor build() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Descriptor intermediateResult = _helper.build();
         
        return intermediateResult;
    }

    public MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
        ImplBlockBuilder_m1_m2_m20_m9_m10 step1 = new ImplBlockBuilder_m1_m2_m20_m9_m10(helper2 .get(), this);
        ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18 step2 = new ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18(helper1 .get(), step1);
         
        return step2;
    }

}
