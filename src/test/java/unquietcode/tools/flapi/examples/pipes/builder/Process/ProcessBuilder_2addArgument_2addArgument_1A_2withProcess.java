
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessBuilder.Head;
import unquietcode.tools.flapi.runtime.ChainInfo;
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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface ProcessBuilder_2addArgument_2addArgument_1A_2withProcess<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> addArgument(String argument);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> addArgument(String name, String value);

    @MethodInfo(type = TransitionType.Ascending, chainInfo = {
        @ChainInfo(type = Head.class, position = 0)
    })
    Head<_ReturnType> pipe();

    @MethodInfo(type = TransitionType.Terminal)
    InputStream run();

    @MethodInfo(type = TransitionType.Lateral)
    @Tracked(atLeast = 1, key = "withProcess_1String_na")
    ProcessBuilder_2addArgument_2addArgument_1A_2pipe<_ReturnType> withProcess(String name);
}
