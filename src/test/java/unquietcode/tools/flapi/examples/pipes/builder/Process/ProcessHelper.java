
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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface ProcessHelper {
    void addArgument(String argument);

    void addArgument(String name, String value);

    void pipe(AtomicReference<ProcessHelper> _helper1);

    InputStream run();

    void withProcess(String name);
}
