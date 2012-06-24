
package unquietcode.tools.flapi.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockHelper {


    void endBlock();

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1);

}
