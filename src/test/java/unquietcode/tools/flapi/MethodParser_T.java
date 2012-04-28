package unquietcode.tools.flapi;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Ben Fagin
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
		Pair<String, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first);
		assertEquals("param name", "name", pair.second);
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
		Pair<String, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first);
		assertEquals("param name", "greeting", pair.second);

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
		Pair<String, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first);
		assertEquals("param name", "name", pair.second);
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

	@Test(expected = MethodParser.ParseException.class)
	public void extraParentheses() {
		String methodSignature = "startBlock(String one, String two()";
		MethodParser parsed = new MethodParser(methodSignature);
	}

	@Test
	public void parameterOrder() {
		String methodSignature = "between(int atLeast, int atMost, String somethingElse, java.lang.Boolean _paramX)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals(parsed.params.get(0).first, "int");
		assertEquals(parsed.params.get(0).second, "atLeast");

		assertEquals(parsed.params.get(1).first, "int");
		assertEquals(parsed.params.get(1).second, "atMost");

		assertEquals(parsed.params.get(2).first, "String");
		assertEquals(parsed.params.get(2).second, "somethingElse");

		assertEquals(parsed.params.get(3).first, "java.lang.Boolean");
		assertEquals(parsed.params.get(3).second, "_paramX");
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

	@Test
	public void emptyWithReturnType() {
		String methodSignature = "String method()";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("no params", 0, parsed.params.size());
	}

	@Test
	public void singleTypeParameterInFront() {
		String methodSignature = "List<String> method()";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("no params", 0, parsed.params.size());
		assertEquals("type with type parameter", "List<String>", parsed.returnType);
	}

	@Test
	public void multipleTypeParameterInFront() {
		String methodSignature = "Map<String, Integer> method()";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("no params", 0, parsed.params.size());
		assertEquals("type with type parameter", "Map<String,Integer>", parsed.returnType);
	}

	@Test
	public void singleTypeParameterInside() {
		String methodSignature = "void method(List<String> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "List<String>", parsed.params.get(0).first);
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void multipleTypeParameterInside() {
		String methodSignature = "void method(Map<String, Integer> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String,Integer>", parsed.params.get(0).first);
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void nestedTypeParameters() {
		String methodSignature = "void method(Map<String, Set<String>> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String,Set<String>>", parsed.params.get(0).first);
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}
}
