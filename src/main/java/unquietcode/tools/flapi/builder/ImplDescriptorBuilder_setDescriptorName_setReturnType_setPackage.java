
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setReturnType_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setReturnType_setPackage
{


    ImplDescriptorBuilder_setDescriptorName_setReturnType_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setReturnType_setPackage setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setReturnType_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName_setReturnType setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setDescriptorName_setReturnType(_helper, _returnValue);
    }

}
