
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 25, 2012 17:55:21 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 25, 2012 17:55:21 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplDescriptorBuilder_enableCondensedClassNames_setReturnType_setStartingMethodName
    implements DescriptorBuilder_enableCondensedClassNames_setReturnType_setStartingMethodName, BuilderImplementation
{

    private final DescriptorHelper _helper;
    private final Object _returnValue;

    ImplDescriptorBuilder_enableCondensedClassNames_setReturnType_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
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

    public DescriptorBuilder_setReturnType_setStartingMethodName enableCondensedClassNames() {
        _helper.enableCondensedClassNames();
        ImplDescriptorBuilder_setReturnType_setStartingMethodName step1 = new ImplDescriptorBuilder_setReturnType_setStartingMethodName(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public MethodBuilder_addBlockChain addBlockReference(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addBlockReference(blockName, methodSignature, helper1);
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
         
        return step1;
    }

    public MethodBuilder_addBlockChain startBlock(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(methodSignature, helper1, helper2);
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper2 .get(), this);
        ImplMethodBuilder_addBlockChain step2 = new ImplMethodBuilder_addBlockChain(helper1 .get(), step1);
         
        return step2;
    }

    public DescriptorBuilder_enableCondensedClassNames_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
        ImplDescriptorBuilder_enableCondensedClassNames_setReturnType step1 = new ImplDescriptorBuilder_enableCondensedClassNames_setReturnType(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public MethodBuilder_addBlockChain addMethod(String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        _helper.addMethod(methodSignature, helper1);
        ImplMethodBuilder_addBlockChain step1 = new ImplMethodBuilder_addBlockChain(helper1 .get(), this);
         
        return step1;
    }

    public MethodBuilder_addBlockChain startBlock(String blockName, String methodSignature) {
        ObjectWrapper<MethodHelper> helper1 = new ObjectWrapper<MethodHelper>();
        ObjectWrapper<BlockHelper> helper2 = new ObjectWrapper<BlockHelper>();
        _helper.startBlock(blockName, methodSignature, helper1, helper2);
        ImplBlockBuilder step1 = new ImplBlockBuilder(helper2 .get(), this);
        ImplMethodBuilder_addBlockChain step2 = new ImplMethodBuilder_addBlockChain(helper1 .get(), step1);
         
        return step2;
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

    public DescriptorBuilder_enableCondensedClassNames_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
        ImplDescriptorBuilder_enableCondensedClassNames_setStartingMethodName step1 = new ImplDescriptorBuilder_enableCondensedClassNames_setStartingMethodName(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
