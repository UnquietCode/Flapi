
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setStartingMethodName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setStartingMethodName_setPackage
{


    ImplDescriptorBuilder_showLog_setStartingMethodName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setStartingMethodName_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setStartingMethodName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_showLog_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName(_helper, _returnValue);
    }

}
