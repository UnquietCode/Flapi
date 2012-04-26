
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog_setPackage
{


    ImplDescriptorBuilder_showLog_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setPackage showLog(boolean value) {
        _helper.showLog(value);
         
        return new ImplDescriptorBuilder_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_showLog setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_showLog(_helper, _returnValue);
    }

}
