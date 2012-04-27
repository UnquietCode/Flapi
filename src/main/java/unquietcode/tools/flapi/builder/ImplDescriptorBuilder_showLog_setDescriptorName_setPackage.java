
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setDescriptorName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setDescriptorName_setPackage
{


    ImplDescriptorBuilder_showLog_setDescriptorName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_showLog_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog_setDescriptorName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog_setDescriptorName(_helper, _returnValue);
    }

}
