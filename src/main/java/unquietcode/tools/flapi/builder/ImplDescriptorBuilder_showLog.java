
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_showLog
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_showLog
{


    ImplDescriptorBuilder_showLog(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder showLog(boolean value) {
        _helper.showLog(value);
         
        return this;
    }

}
