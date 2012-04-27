
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setReturnType_setPackage_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setReturnType_setPackage_setStartingMethodName
{


    ImplDescriptorBuilder_showLog_setReturnType_setPackage_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setPackage_setStartingMethodName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setReturnType_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setPackage_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_showLog_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType_setPackage(_helper, _returnValue);
    }

}
