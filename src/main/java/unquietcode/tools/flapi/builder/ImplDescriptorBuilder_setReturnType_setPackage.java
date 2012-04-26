
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setReturnType_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setReturnType_setPackage
{


    ImplDescriptorBuilder_setReturnType_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setPackage setReturnType(Class returnType) {
        _helper.setReturnType(returnType);
         
        return new ImplDescriptorBuilder_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setReturnType setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setReturnType(_helper, _returnValue);
    }

}
