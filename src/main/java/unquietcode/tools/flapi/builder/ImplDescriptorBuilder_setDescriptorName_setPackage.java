
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setPackage
{


    ImplDescriptorBuilder_setDescriptorName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName(_helper, _returnValue);
    }

}
