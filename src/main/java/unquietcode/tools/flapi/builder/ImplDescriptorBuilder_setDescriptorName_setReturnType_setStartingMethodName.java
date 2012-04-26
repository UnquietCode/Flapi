
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName
{


    ImplDescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setStartingMethodName_setReturnType setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setStartingMethodName_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType(_helper, _returnValue);
    }

}
