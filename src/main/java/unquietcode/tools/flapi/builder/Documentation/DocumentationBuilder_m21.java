
package unquietcode.tools.flapi.builder.Documentation;

import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 14, 2013 21:16:25 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 14, 2013 21:16:25 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DocumentationBuilder_m21 <_ReturnType> {
    /**
     * add more content to the Javadocs
     */
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    DocumentationBuilder_m21 <_ReturnType> addContent(String content);

    /**
     * finish writing the documentation
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType finish();
}
