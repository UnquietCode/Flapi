
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import unquietcode.tools.flapi.support.MethodInfo;
import unquietcode.tools.flapi.support.TransitionType;

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
 * Generated on July 01, 2013 22:50:06 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 22:50:06 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder_pipe<_ReturnType> {
    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = TransitionType.Recursive, chain = {

    })
    ProcessBuilder_pipe<_ReturnType> addArgument(String argument);

    @MethodInfo(checkInvocations = false, checkParentInvocations = false, type = TransitionType.Recursive, chain = {

    })
    ProcessBuilder_pipe<_ReturnType> addArgument(String name, String value);

    @MethodInfo(checkInvocations = true, checkParentInvocations = false, type = TransitionType.Ascending, chain = {
        ProcessBuilder_pipe_withProcess.class
    })
    ProcessBuilder_pipe_withProcess<_ReturnType> pipe();

    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = TransitionType.Terminal, chain = {

    })
    InputStream run();
}
