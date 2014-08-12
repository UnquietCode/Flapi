
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.Tracked;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;
import java.io.InputStream;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 12, 2014 13:17:30 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder {
    public interface Start<_ReturnType>
        extends ProcessBuilder_2addArgument_4f_2addArgument_1A_4f_2withProcess_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Recursive)
        ProcessBuilder.Start<_ReturnType> addArgument(String argument);

        @MethodInfo(type = TransitionType.Recursive)
        ProcessBuilder.Start<_ReturnType> addArgument(String name, String value);

        @MethodInfo(type = TransitionType.Ascending, chain = {
            ProcessBuilder.Start.class
        })
        ProcessBuilder.Start<_ReturnType> pipe();

        @MethodInfo(type = TransitionType.Terminal)
        InputStream run();

        @MethodInfo(type = TransitionType.Lateral)
        @Tracked(atLeast = 1, key = "withProcess$String_name")
        ProcessBuilder_2addArgument_4f_2addArgument_1A_4f_2pipe_4f<_ReturnType> withProcess(String name);
    }
}
