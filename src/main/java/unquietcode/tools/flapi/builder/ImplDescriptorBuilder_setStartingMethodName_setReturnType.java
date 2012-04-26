
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setStartingMethodName_setReturnType
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setStartingMethodName_setReturnType
{


    ImplDescriptorBuilder_setStartingMethodName_setReturnType(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setStartingMethodName(_helper, _returnValue);
    }

}
