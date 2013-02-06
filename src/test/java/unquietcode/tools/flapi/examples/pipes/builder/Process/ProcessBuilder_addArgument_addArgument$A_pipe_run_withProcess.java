
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import java.io.InputStream;
import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 05, 2013 22:11:40 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 05, 2013 22:11:40 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess<_ReturnType >{


    ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess<_ReturnType> addArgument(String argument);

    ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess<_ReturnType> addArgument(String name, String value);

    ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess<_ReturnType> pipe();

    InputStream run();

    ProcessBuilder_addArgument_addArgument$A_pipe_run<_ReturnType> withProcess(String name);

}
