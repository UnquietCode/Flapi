
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setDescriptorName_setPackage_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setDescriptorName_setPackage_setStartingMethodName
{


    ImplDescriptorBuilder_showLog_setDescriptorName_setPackage_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setPackage_setStartingMethodName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setPackage_setStartingMethodName setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setPackage_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setDescriptorName_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog_setDescriptorName_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setDescriptorName_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_showLog_setDescriptorName_setPackage(_helper, _returnValue);
    }

}
