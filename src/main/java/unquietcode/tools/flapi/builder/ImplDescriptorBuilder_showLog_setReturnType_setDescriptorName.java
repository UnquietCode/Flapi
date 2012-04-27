
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setReturnType_setDescriptorName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setReturnType_setDescriptorName
{


    ImplDescriptorBuilder_showLog_setReturnType_setDescriptorName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setDescriptorName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setReturnType_setDescriptorName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setDescriptorName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_showLog_setDescriptorName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType(_helper, _returnValue);
    }

}
