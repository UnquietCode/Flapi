
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType_setStartingMethodName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType_setStartingMethodName_setPackage
{


    ImplDescriptorBuilder_setReturnType_setStartingMethodName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setStartingMethodName_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setStartingMethodName_setReturnType setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setStartingMethodName_setReturnType(_helper, _returnValue);
    }

}
