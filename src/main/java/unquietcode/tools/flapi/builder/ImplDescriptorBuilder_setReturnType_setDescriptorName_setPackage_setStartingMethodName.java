
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType_setDescriptorName_setPackage_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType_setDescriptorName_setPackage_setStartingMethodName
{


    ImplDescriptorBuilder_setReturnType_setDescriptorName_setPackage_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setPackage_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType_setPackage_setStartingMethodName setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setReturnType_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType_setDescriptorName_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setReturnType_setDescriptorName_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType_setDescriptorName_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setReturnType_setDescriptorName_setPackage(_helper, _returnValue);
    }

}
