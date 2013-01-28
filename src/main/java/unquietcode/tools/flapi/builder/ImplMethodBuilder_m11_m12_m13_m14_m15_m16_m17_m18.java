
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
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
 * Generated on January 27, 2013 22:16:43 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "January 27, 2013 22:16:43 CST", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18
    implements MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18, BuilderImplementation
{

    private final MethodHelper _helper;
    private final Object _returnValue;

    ImplMethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18(MethodHelper helper, Object returnValue) {
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

    public BlockChainBuilder_m11_m20_m10 addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
        ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18 step1 = new ImplMethodBuilder_m12_m13_m14_m15_m16_m17_m18(_helper, _returnValue);
        ImplBlockChainBuilder_m11_m20_m10 step2 = new ImplBlockChainBuilder_m11_m20_m10(helper1 .get(), step1);
         
        _transferInvocations(step2);
        return step2;
    }

    public Object any() {
        _checkInvocations();
        _helper.any();
         
        return _returnValue;
    }

    public Object atLeast(int num) {
        _checkInvocations();
        _helper.atLeast(num);
         
        return _returnValue;
    }

    public Object atMost(int num) {
        _checkInvocations();
        _helper.atMost(num);
         
        return _returnValue;
    }

    public Object between(int atLeast, int atMost) {
        _checkInvocations();
        _helper.between(atLeast, atMost);
         
        return _returnValue;
    }

    public Object exactly(int num) {
        _checkInvocations();
        _helper.exactly(num);
         
        return _returnValue;
    }

    public Object last() {
        _checkInvocations();
        _helper.last();
         
        return _returnValue;
    }

    public Object last(Class returnType) {
        _checkInvocations();
        _helper.last(returnType);
         
        return _returnValue;
    }

}
