
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 12, 2012 24:38:04 CDT using version 0.1
 * 
 */
public interface DescriptorHelper {


    Descriptor _getReturnValue();

    void setReturnType(Class returnType);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void setStartingMethodName(String methodName);

    void enableCondensedClassNames(boolean value);

    void setDescriptorName(String descriptorName);

    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void setPackage(String packageName);

    void build();

}
