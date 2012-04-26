
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setDescriptorName_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setDescriptorName_setStartingMethodName
{


    ImplDescriptorBuilder_setDescriptorName_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder_setStartingMethodName setDescriptorName(String descriptorName) {
        _helper.setDescriptorName(descriptorName);
         
        return new ImplDescriptorBuilder_setStartingMethodName(_helper, _returnValue);
    }

    public DescriptorBuilder_setDescriptorName setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return new ImplDescriptorBuilder_setDescriptorName(_helper, _returnValue);
    }

}
