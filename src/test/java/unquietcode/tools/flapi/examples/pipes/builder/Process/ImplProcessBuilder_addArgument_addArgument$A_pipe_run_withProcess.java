
package unquietcode.tools.flapi.examples.pipes.builder.Process;

import java.io.InputStream;
import java.lang.reflect.Field;
import javax.annotation.Generated;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ExpectedInvocationsException;
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
public class ImplProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess
    implements ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess, BuilderImplementation
{

    private final ProcessHelper _helper;
    private final Object _returnValue;
    int ic_Process_withProcess$String_name = 1;

    public ImplProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess(ProcessHelper helper, Object returnValue) {
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
        Class clazz = next.getClass();
         
        try {
            Field field = clazz.getDeclaredField("ic_Process_withProcess$String_name");
            field.setInt(next, ic_Process_withProcess$String_name);
        } catch (Exception _x) {
            // nothing
        }
    }

    public void _checkInvocations() {
        if (ic_Process_withProcess$String_name > 0) {
            throw new ExpectedInvocationsException("Expected at least 1 invocations of method 'withProcess(String name)'.");
        }
    }

    public ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess addArgument(String argument) {
        _helper.addArgument(argument);
         
        return this;
    }

    public ProcessBuilder_addArgument_addArgument$A_pipe_run_withProcess addArgument(String name, String value) {
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

    public ProcessBuilder_addArgument_addArgument$A_pipe_run withProcess(String name) {
        --ic_Process_withProcess$String_name;
        _helper.withProcess(name);
        ImplProcessBuilder_addArgument_addArgument$A_pipe_run step1 = new ImplProcessBuilder_addArgument_addArgument$A_pipe_run(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

}
