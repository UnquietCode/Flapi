/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.MethodParser.JavaType;

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
		
		assertEquals("return type", "String", parsed.returnType.typeName);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Pair<JavaType, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first.typeName);
		assertEquals("param name", "name", pair.second);
	}
	
	@Test
	public void varargOnly() {
		String methodSignature = "String doWork(String...names)";
		MethodParser parsed = new MethodParser(methodSignature);
	
		assertEquals("return type", "String", parsed.returnType.typeName);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("no pairs", 0, parsed.params.size());

		assertEquals("one vararg type", "String", parsed.varargType.typeName);
		assertEquals("one vararg name", "names", parsed.varargName);
	}
	
	@Test
	public void withVararg() {
		String methodSignature = "String doWork(String greeting, String...names)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("return type", "String", parsed.returnType.typeName);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Pair<JavaType, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first.typeName);
		assertEquals("param name", "greeting", pair.second);

		assertEquals("one vararg type", "String", parsed.varargType.typeName);
		assertEquals("one vararg name", "names", parsed.varargName);
	}

	@Test
	public void borked() {
		String methodSignature = "void \n something  ( \tString\t  name)\n";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("return type", "void", parsed.returnType.typeName);
		assertEquals("method name", "something", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Pair<JavaType, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first.typeName);
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
		assertEquals("Object", parsed.varargType.typeName);
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

		assertEquals(parsed.params.get(0).first.typeName, "int");
		assertEquals(parsed.params.get(0).second, "atLeast");

		assertEquals(parsed.params.get(1).first.typeName, "int");
		assertEquals(parsed.params.get(1).second, "atMost");

		assertEquals(parsed.params.get(2).first.typeName, "String");
		assertEquals(parsed.params.get(2).second, "somethingElse");

		assertEquals(parsed.params.get(3).first.typeName, "java.lang.Boolean");
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
		assertEquals("type with type parameter", "List<String>", parsed.returnType.toString());
	}

	@Test
	public void multipleTypeParameterInFront() {
		String methodSignature = "Map<String, Integer> method()";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("no params", 0, parsed.params.size());
		assertEquals("type with type parameter", "Map<String, Integer>", parsed.returnType.toString());
	}

	@Test
	public void singleTypeParameterInside() {
		String methodSignature = "void method(List<String> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "List<String>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void multipleTypeParameterInside() {
		String methodSignature = "void method(Map<String, Integer> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String, Integer>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void nestedTypeParameters() {
		String methodSignature = "void method(Map<String, Set<String>> param)";
		MethodParser parsed = new MethodParser(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String, Set<String>>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test(expected=MethodParser.ValidationException.class)
	public void keywordsAreInvalid() throws Exception {
		String methodSignature = "void continue(int x)";
		MethodParser parsed = new MethodParser(methodSignature);
		parsed.validate();
	}

	@Test
	public void arrayTypeAsReturn() {
		String methodSignature = "int[] method()";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 1, parsed.returnType.arrayDepth);
	}

	@Test
	public void arrayTypeAsParameter() {
		String methodSignature = "void method(int[] p1, String p2)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
		assertEquals("not array type", 0, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void multidimensionalArrayTypeAsParameter() {
		String methodSignature = "void method(int[] [] p1, String p2)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 2, parsed.params.get(0).first.arrayDepth);
		assertEquals("not array type", 0, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void arrayTypeAsParameterAfterIdentifier() {
		String methodSignature = "void method(int p1, String p2[])";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("not array type", 0, parsed.params.get(0).first.arrayDepth);
		assertEquals("array type", 1, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void arrayTypeAsTypeParameter() {
		String methodSignature = "void method(List<byte[]> list)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("not array type", 0, parsed.params.get(0).first.arrayDepth);
		assertEquals("array type", 1, parsed.params.get(0).first.typeParameters.get(0).arrayDepth);
	}

	@Test(expected=MethodParser.ParseException.class)
	public void arrayTypesWithTypeParametersNotOk() {
		String methodSignature = "void method(List[]<String> p1)";
		new MethodParser(methodSignature);
	}

	@Test
	public void arrayTypesWithTypeParametersOk1() {
		String methodSignature = "void method(List<String>[] p1)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
	}

	@Test
	public void arrayTypesWithTypeParametersOk2() {
		String methodSignature = "void method(List<String> p1[])";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
	}

	@Test
	public void multidimensionalArrayTypesWithTypeParameters() {
		String methodSignature = "void method(List<String>[][][] p1)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("string type", "List", parsed.params.get(0).first.typeName);
		assertEquals("string type", "String", parsed.params.get(0).first.typeParameters.get(0).typeName);
		assertEquals("array type", 3, parsed.params.get(0).first.arrayDepth);
	}

	@Test(expected=MethodParser.ParseException.class)
	public void duplicateArrayIndexers() {
		String methodSignature = "void method(int[] p1[])";
		new MethodParser(methodSignature);
	}

	@Test(expected=MethodParser.ParseException.class)
	public void duplicateArrayIndexerWithTypeParam() {
		String methodSignature = "void method(List<String>[] array[])";
		new MethodParser(methodSignature);
	}

	@Test
	public void multiDimensionalArray() {
		String methodSignature = "void method(int[][] p1)";
		MethodParser parsed = new MethodParser(methodSignature);
		assertEquals("array type", 2, parsed.params.get(0).first.arrayDepth);
	}

	@Test(expected=MethodParser.ParseException.class)
	public void multiDimensionalDuplicate() {
		String methodSignature = "void method(int[][] p1[])";
		new MethodParser(methodSignature);
	}

	@Test
	public void methodEqualityTests() {

		// -- General --

		// different parameter types
		testMethodEquality(false, "void method(String p1)", "void method(int p1)");

		// same parameter types
		testMethodEquality(true, "void method(int p1)", "void method(int p1)");

		// same params, different return type
		testMethodEquality(false, "void method(String p1)", "String method(String p1)");

		// same everything but method name
		testMethodEquality(false, "void methodA(int p1)", "void methodB(int p1)");

		// with and without void
		testMethodEquality(true, "void method(int a)", "method(int a)");

		// with generic erasure
		testMethodEquality(true, "void method(List<String> list)", "void method(List<Integer> list)");


		// -- Arrays --

		// with primitive array parameters
		testMethodEquality(false, "method(int a)", "method(int[] a)");

		// with Object array parameters
		testMethodEquality(false, "method(Object a)", "method(Object[] a)");

		// with flexible bracket position
		testMethodEquality(true, "method(Object[] a)", "method(Object a[])");


		// -- Vararg --

		// one with vararg
		testMethodEquality(false, "void method(String p1, int p2)", "void method(String p1, int p2, int...p3)");

		// vararg only, same return type
		testMethodEquality(true, "void method(String...p1)", "void method(String...p1)");

		// vararg only, different return type
		testMethodEquality(false, "void method(String...p1)", "int method(String... p1)");

		// vararg and array
		testMethodEquality(true, "method(String...p1)", "method(String[] p2)");

		// array and vararg
		testMethodEquality(true, "method(String[] p1)", "method(String...p2)");
	}


	private static void testMethodEquality(boolean expected, String method1, String method2) {
		MethodParser m1 = new MethodParser(method1);
		MethodParser m2 = new MethodParser(method2);

		if (expected) {
			assertTrue("expected equal methods (<"+method1+"> <"+method2+">)", m1.compilerEquivalent(m2));
		} else {
			assertFalse("expected unequal methods (<"+method1+"> <"+method2+">)", m1.compilerEquivalent(m2));
		}
	}
}