
package unquietcode.tools.flapi.builder;

import java.lang.reflect.Field;
import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ExpectedInvocationsException;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDescriptorBuilder_setDescriptorName_setStartingMethodName
    implements DescriptorBuilder_setDescriptorName_setStartingMethodName, BuilderImplementation
{

    private final DescriptorHelper _helper;
    private final BuilderImplementation _returnValue;
    int ic_Descriptor_setDescriptorName$String_descriptorName = 1;
    int ic_Descriptor_setPackage$String_packageName = 1;

    ImplDescriptorBuilder_setDescriptorName_setStartingMethodName(DescriptorHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
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

    public MethodBuilder_addBlockChain addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
         
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
        _transferInvocations(step1);
        return step1;
    }

    public Descriptor build() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Descriptor intermediateResult = _helper.build();
         
        return intermediateResult;
    }

    public MethodBuilder_addBlockChain startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
         
        ImplBlockBuilder step2 = new ImplBlockBuilder(helper2 .get(), this);
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), step2);
        _transferInvocations(step1);
        return step1;
    }

    public DescriptorBuilder_setStartingMethodName setDescriptorName(String descriptorName) {
        --ic_Descriptor_setDescriptorName$String_descriptorName;
        _helper.setDescriptorName(descriptorName);
         
        DescriptorBuilder_setStartingMethodName retval = new ImplDescriptorBuilder_setStartingMethodName(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

    public DescriptorBuilder_setDescriptorName setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        DescriptorBuilder_setDescriptorName retval = new ImplDescriptorBuilder_setDescriptorName(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
