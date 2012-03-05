package unquietcode.tools.flapi;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public class MethodParser_T {

	@Test
	public void basic() {
		String methodSignature = "String doWork(String name)";
		MethodParser parsed = new MethodParser(methodSignature);
		
		assertEquals("return type", "String", parsed.returnType);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Map.Entry<String, String> pair = parsed.params.entrySet().iterator().next();
		assertEquals("param name", "name", pair.getKey());
		assertEquals("param name", "String", pair.getValue());
	}
	
	@Test
	public void varargOnly() {
		String methodSignature = "String doWork(String...names)";
		MethodParser parsed = new MethodParser(methodSignature);
	
		assertEquals("return type", "String", parsed.returnType);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("no pairs", 0, parsed.params.size());

		assertEquals("one vararg type", "String", parsed.varargType);
		assertEquals("one vararg name", "names", parsed.varargName);
	}
	
	@Test
	public void withVararg() {
		String methodSignature = "String doWork(String greeting, String...names)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("return type", "String", parsed.returnType);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Map.Entry<String, String> pair = parsed.params.entrySet().iterator().next();
		assertEquals("param name", "greeting", pair.getKey());
		assertEquals("param name", "String", pair.getValue());

		assertEquals("one vararg type", "String", parsed.varargType);
		assertEquals("one vararg name", "names", parsed.varargName);
	}

	@Test
	public void borked() {
		String methodSignature = "void \n something  ( \tString\t  name)\n";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("return type", "void", parsed.returnType);
		assertEquals("method name", "something", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Map.Entry<String, String> pair = parsed.params.entrySet().iterator().next();
		assertEquals("param name", "name", pair.getKey());
		assertEquals("param name", "String", pair.getValue());
	}

	@Test(expected = MethodParser.ParseException.class)
	public void duplicateName() {
		String methodSignature = "void something(String value, int value)";
		MethodParser parsed = new MethodParser(methodSignature);
	}
	
	@Test
	public void aFailedVararg() {
		String methodSignature = "void debug(String message, Object...data)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("Object", parsed.varargType);
		assertEquals("data", parsed.varargName);
	}
	
	@Test
	public void allowsNoReturnType() {
		String methodSignature = "debug(String message)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("no return type", null, parsed.returnType);
	}

	@Test
	public void emptyTest() {
		String methodSignature = "method()";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("no params", 0, parsed.params.size());
	}
}
