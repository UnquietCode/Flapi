
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setStartingMethodName_setPackage
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setStartingMethodName_setPackage
{


    ImplDescriptorBuilder_setStartingMethodName_setPackage(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setPackage setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setPackage(_helper, _returnValue);
    }

    public DescriptorBuilder_setStartingMethodName setPackage(String packageName) {
        _helper.setPackage(packageName);
         
        return new ImplDescriptorBuilder_setStartingMethodName(_helper, _returnValue);
    }

}
