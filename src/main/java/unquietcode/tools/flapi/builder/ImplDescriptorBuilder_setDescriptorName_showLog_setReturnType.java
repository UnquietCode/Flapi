
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_showLog_setReturnType
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_showLog_setReturnType
{


    ImplDescriptorBuilder_setDescriptorName_showLog_setReturnType(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setReturnType setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog(_helper, _returnValue);
    }

}
