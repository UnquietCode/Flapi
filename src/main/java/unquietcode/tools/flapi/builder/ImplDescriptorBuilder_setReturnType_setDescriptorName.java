
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType_setDescriptorName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType_setDescriptorName
{


    ImplDescriptorBuilder_setReturnType_setDescriptorName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setDescriptorName setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setReturnType(_helper, _returnValue);
    }

}
