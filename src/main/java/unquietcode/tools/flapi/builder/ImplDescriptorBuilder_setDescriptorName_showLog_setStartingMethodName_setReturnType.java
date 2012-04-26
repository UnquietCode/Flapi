
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType
{


    ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName_setReturnType(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType_setStartingMethodName setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog_setStartingMethodName(_helper, _returnValue);
    }

}
