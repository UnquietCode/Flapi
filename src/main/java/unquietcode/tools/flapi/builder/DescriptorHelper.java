
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 24:33:22 CDT using version 0.2
 * 
 */
public interface DescriptorHelper {


    Descriptor _getReturnValue();

    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void build();

    void enableCondensedClassNames(boolean value);

    void setStartingMethodName(String methodName);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void setReturnType(Class returnType);

    void setPackage(String packageName);

    void setDescriptorName(String descriptorName);

}
