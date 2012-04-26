
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_showLog_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_showLog_setPackage
{


    ImplDescriptorBuilder_setDescriptorName_showLog_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_showLog_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_showLog setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName_showLog(_helper, _returnValue);
    }

}
