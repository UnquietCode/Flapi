
package unquietcode.tools.flapi.examples.pipes.builder.Process;

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
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface ProcessBuilder_pipe<_ReturnType> {
    ProcessBuilder_pipe<_ReturnType> addArgument(String argument);

    ProcessBuilder_pipe<_ReturnType> addArgument(String name, String value);

    ProcessBuilder_pipe_withProcess<_ReturnType> pipe();

    InputStream run();
}
