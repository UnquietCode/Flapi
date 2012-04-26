
package unquietcode.tools.flapi.builder;


public class ImplDescriptorBuilder_setStartingMethodName
    extends ImplDescriptorBuilder
    implements DescriptorBuilder_setStartingMethodName
{


    ImplDescriptorBuilder_setStartingMethodName(DescriptorHelper helper, Object returnValue) {
        super(helper, returnValue);
    }

    public DescriptorBuilder setStartingMethodName(String methodName) {
        _helper.setStartingMethodName(methodName);
         
        return this;
    }

}
