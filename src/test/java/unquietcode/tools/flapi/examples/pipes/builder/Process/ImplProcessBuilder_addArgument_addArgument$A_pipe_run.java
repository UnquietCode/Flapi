
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import java.io.InputStream;
import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;
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
public class ImplProcessBuilder_addArgument_addArgument$A_pipe_run
    implements ProcessBuilder_addArgument_addArgument$A_pipe_run, BuilderImplementation
{

    private final ProcessHelper _helper;
    private final Object _returnValue;

    public ImplProcessBuilder_addArgument_addArgument$A_pipe_run(ProcessHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public ProcessBuilder_addArgument_addArgument$A_pipe_run addArgument(String argument) {
        _helper.addArgument(argument);
         
        return this;
    }

    public ProcessBuilder_addArgument_addArgument$A_pipe_run addArgument(String name, String value) {
        _helper.addArgument(name, value);
         
        return this;
    }

    public ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess pipe() {
        _checkInvocations();
        ObjectWrapper<ProcessHelper> helper1 = new ObjectWrapper<ProcessHelper>();
        _helper.pipe(helper1);
        ImplProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess step1 = new ImplProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess(helper1 .get(), _returnValue);
         
        return step1;
    }

    public InputStream run() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        InputStream intermediateResult = _helper.run();
         
        return intermediateResult;
    }

}
