
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setReturnType
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setReturnType
{


    ImplDescriptorBuilder_showLog_setReturnType(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setReturnType(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_showLog(_helper, _returnValue);
    }

}
