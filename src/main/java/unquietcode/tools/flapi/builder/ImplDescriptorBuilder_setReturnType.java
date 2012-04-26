
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType
{


    ImplDescriptorBuilder_setReturnType(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return this;
    }

}
