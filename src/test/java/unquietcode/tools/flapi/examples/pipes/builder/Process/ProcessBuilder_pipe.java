
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.pipes.builder.Process;

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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder_pipe<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    ProcessBuilder_pipe<_ReturnType> addArgument(String argument);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    ProcessBuilder_pipe<_ReturnType> addArgument(String name, String value);

    @MethodInfo(type = TransitionType.Ascending, chain = {
        ProcessBuilder_pipe_withProcess.class
    })
    ProcessBuilder_pipe_withProcess<_ReturnType> pipe();

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    InputStream run();
}
