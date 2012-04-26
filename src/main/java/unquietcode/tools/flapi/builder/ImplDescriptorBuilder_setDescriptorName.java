
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName
{


    ImplDescriptorBuilder_setDescriptorName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return this;
    }

}
