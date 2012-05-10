
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorBuilderException;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:25:34 CDT using version 0.1
 * 
 */
public class DescriptorGenerator {


    @SuppressWarnings("unchecked")
    public static DescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog<Descriptor> create(DescriptorHelper helper) {
        if (helper == null) {
            throw new DescriptorBuilderException("Helper cannot be null.");
        }
         
        return new ImplDescriptorBuilder_enableCondensedClassNames_setDescriptorName_setPackage_setReturnType_setStartingMethodName_showLog(helper, helper._getReturnValue());
    }

}
