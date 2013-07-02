
package unquietcode.tools.flapi.builder.Documentation;

import unquietcode.tools.flapi.support.MethodInfo;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 20:13:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 20:13:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface DocumentationBuilder_m23 <_ReturnType> {
    /**
     * add more content to the Javadocs
     */
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = 0, chain = {

    })
    DocumentationBuilder_m23 <_ReturnType> addContent(String content);

    /**
     * finish writing the documentation
     */
    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = 3, chain = {

    })
    _ReturnType finish();
}
