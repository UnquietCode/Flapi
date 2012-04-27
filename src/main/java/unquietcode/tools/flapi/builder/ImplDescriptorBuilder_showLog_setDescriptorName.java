
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setDescriptorName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setDescriptorName
{


    ImplDescriptorBuilder_showLog_setDescriptorName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setDescriptorName showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog(_helper, _returnValue);
    }

}
