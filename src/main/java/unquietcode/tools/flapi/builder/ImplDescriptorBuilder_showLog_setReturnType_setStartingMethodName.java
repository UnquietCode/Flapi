
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setReturnType_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setReturnType_setStartingMethodName
{


    ImplDescriptorBuilder_showLog_setReturnType_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setStartingMethodName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setReturnType_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setStartingMethodName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_showLog_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType(_helper, _returnValue);
    }

}
