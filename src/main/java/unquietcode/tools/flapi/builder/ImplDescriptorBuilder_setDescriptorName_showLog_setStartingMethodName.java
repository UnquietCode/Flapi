
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName
{


    ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setStartingMethodName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog(_helper, _returnValue);
    }

}
