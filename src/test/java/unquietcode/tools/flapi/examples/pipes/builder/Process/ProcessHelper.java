
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import javax.annotation.Generated;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;


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
public interface ProcessHelper {
    void addArgument(String argument);

    void addArgument(String name, String value);

    void pipe(AtomicReference<ProcessHelper> _helper1);

    InputStream run();

    void withProcess(String name);
}
