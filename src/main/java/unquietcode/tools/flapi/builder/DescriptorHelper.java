
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on November 25, 2012 17:55:21 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "November 25, 2012 17:55:21 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface DescriptorHelper {


    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void setPackage(String packageName);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void startBlock(String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void setStartingMethodName(String methodName);

    void enableCondensedClassNames();

    Descriptor build();

    void setDescriptorName(String descriptorName);

    void setReturnType(Class returnType);

}
