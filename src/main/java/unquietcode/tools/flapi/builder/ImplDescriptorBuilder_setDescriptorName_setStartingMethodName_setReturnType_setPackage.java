
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage
{


    ImplDescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setStartingMethodName_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setReturnType_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setStartingMethodName_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

}
