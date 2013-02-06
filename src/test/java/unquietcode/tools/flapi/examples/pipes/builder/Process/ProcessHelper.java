
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import java.io.InputStream;
import javax.annotation.Generated;
import unquietcode.tools.flapi.support.ObjectWrapper;


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
public interface ProcessHelper {


    void addArgument(String argument);

    void addArgument(String name, String value);

    void pipe(ObjectWrapper<ProcessHelper> _helper1);

    InputStream run();

    void withProcess(String name);

}
