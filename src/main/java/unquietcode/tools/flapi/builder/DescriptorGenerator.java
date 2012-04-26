
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorBuilderException;

public class DescriptorGenerator {


    @SuppressWarnings("unchecked")
    public static DescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<Descriptor> create(DescriptorHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplDescriptorBuilder_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog(helper, helper._getReturnValue());
    }

}
