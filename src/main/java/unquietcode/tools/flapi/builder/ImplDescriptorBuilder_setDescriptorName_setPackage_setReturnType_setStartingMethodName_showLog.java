
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog
{


    ImplDescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setStartingMethodName_setReturnType_setPackage(_helper, _returnValue);
    }

}
