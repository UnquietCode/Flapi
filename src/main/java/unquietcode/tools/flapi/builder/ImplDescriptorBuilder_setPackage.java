
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setPackage
{


    ImplDescriptorBuilder_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return this;
    }

}
