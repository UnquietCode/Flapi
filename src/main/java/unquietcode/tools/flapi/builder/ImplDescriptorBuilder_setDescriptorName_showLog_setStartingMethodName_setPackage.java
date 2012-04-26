
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage
{


    ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setStartingMethodName_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName(_helper, _returnValue);
    }

}
