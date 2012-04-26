
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage
{


    ImplDescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setStartingMethodName_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setReturnType_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

}
