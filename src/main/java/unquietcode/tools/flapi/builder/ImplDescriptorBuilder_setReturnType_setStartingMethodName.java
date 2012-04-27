
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType_setStartingMethodName
{


    ImplDescriptorBuilder_setReturnType_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setReturnType(_helper, _returnValue);
    }

}
