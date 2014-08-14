
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessBuilder.Start;
import unquietcode.tools.flapi.runtime.MethodInfo;
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
 * Generated on August 13, 2014 16:08:21 PDT using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.pipes.builder.Process.ProcessHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-13T16:08:21-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder_2addArgument_2addArgument_1A_2pipe<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    ProcessBuilder_2addArgument_2addArgument_1A_2pipe<_ReturnType> addArgument(String argument);

    @MethodInfo(type = TransitionType.Recursive)
    ProcessBuilder_2addArgument_2addArgument_1A_2pipe<_ReturnType> addArgument(String name, String value);

    @MethodInfo(type = TransitionType.Ascending, chain = {
        Start.class
    })
    Start<_ReturnType> pipe();

    @MethodInfo(type = TransitionType.Terminal)
    InputStream run();
}
