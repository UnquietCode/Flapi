
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 01, 2012 21:44:52 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 01, 2012 21:44:52 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplMethodBuilder_addBlockChain
    implements MethodBuilder_addBlockChain, BuilderImplementation
{

    private final MethodHelper _helper;
    private final BuilderImplementation _parent;

    ImplMethodBuilder_addBlockChain(MethodHelper helper, BuilderImplementation parent) {
        _helper = helper;
        _parent = parent;
    }

    public BuilderImplementation _getParent() {
        return _parent;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public BuilderImplementation any() {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.any();
         
        return _parent;
    }

    public BuilderImplementation atLeast(int num) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.atLeast(num);
         
        return _parent;
    }

    public BuilderImplementation atMost(int num) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.atMost(num);
         
        return _parent;
    }

    public BuilderImplementation between(int atLeast, int atMost) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.between(atLeast, atMost);
         
        return _parent;
    }

    public BuilderImplementation exactly(int num) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.exactly(num);
         
        return _parent;
    }

    public BuilderImplementation last() {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.last();
         
        return _parent;
    }

    public BuilderImplementation last(Class returnType) {
        BuilderImplementation cur = _parent;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        _helper.last(returnType);
         
        return _parent;
    }

    public BlockChainBuilder_addBlockChain addBlockChain() {
        ObjectWrapper<BlockChainHelper> helper1 = new ObjectWrapper<BlockChainHelper>();
        _helper.addBlockChain(helper1);
         
        ImplBlockChainBuilder_addBlockChain step1 = new ImplBlockChainBuilder_addBlockChain(helper1 .get(), new ImplMethodBuilder(_helper, _parent));
        _transferInvocations(step1);
        return step1;
    }

}
