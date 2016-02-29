
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessBuilder.Start;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;

import javax.annotation.Generated;
import java.util.function.Supplier;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public class ProcessGenerator {
    public static Start begin(ProcessHelper helper, ExecutionListener... listeners) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
        handler.addListeners(listeners);
        return handler._proxy(Start.class);
    }

    public static ProcessFactory factory(final Supplier<ProcessHelper> provider, final ExecutionListener... listeners) {
        return new ProcessFactory() {
            public Start begin() {
                ProcessHelper helper = provider.get();
                return ProcessGenerator.begin(helper, listeners);
            }
        };
    }
}
