
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessBuilder.$;
import unquietcode.tools.flapi.support.BlockInvocationHandler;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 21:53:50 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 21:53:50 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ProcessGenerator {
    @SuppressWarnings("unchecked")
    public static $<Void> begin(ProcessHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return new BlockInvocationHandler(helper, null)._proxy($.class);
    }
}
