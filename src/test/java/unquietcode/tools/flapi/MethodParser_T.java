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
import unquietcode.tools.flapi.java.JavaType;
import unquietcode.tools.flapi.java.MethodSignature;
import unquietcode.tools.flapi.java.MethodSignature.ValidationException;
import unquietcode.tools.flapi.java.ParseException;

import static org.junit.Assert.*;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class MethodParser_T {

	@Test
	public void voidTypes() throws ParseException {
		JavaType t1 = JavaType.from("void");
		JavaType t2 = JavaType.from("Void");
		JavaType t3 = JavaType.from(Void.class.getName());
		JavaType t4 = JavaType.from(void.class.getName());
		JavaType t5 = JavaType.from("java.lang.String");

		assertTrue(t1.isVoid());
		assertTrue(t2.isVoid());
		assertTrue(t3.isVoid());
		assertTrue(t4.isVoid());
		assertFalse(t5.isVoid());
	}

	@Test
	public void basic() throws ParseException {
		String methodSignature = "String doWork(String name)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		
		assertEquals("return type", "String", parsed.returnType.typeName);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Pair<JavaType, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first.typeName);
		assertEquals("param name", "name", pair.second);
	}
	
	@Test
	public void varargOnly() throws ParseException {
		String methodSignature = "String doWork(String...names)";
		MethodSignature parsed = new MethodSignature(methodSignature);
	
		assertEquals("return type", "String", parsed.returnType.typeName);
		assertEquals("method name", "doWork", parsed.methodName);

		assertEquals("no pairs", 0, parsed.params.size());

		assertEquals("one vararg type", "String", parsed.varargType.typeName);
		assertEquals("one vararg name", "names", parsed.varargName);
	}
	
	@Test
	public void withVararg() throws ParseException {
		String methodSignature = "String doWork(String greeting, String...names)";
		MethodSignature parsed = new MethodSignature(methodSignature);

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
	public void borked() throws ParseException {
		String methodSignature = "void \n something  ( \tString\t  name)\n";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("return type", "void", parsed.returnType.typeName);
		assertEquals("method name", "something", parsed.methodName);

		assertEquals("one pair", 1, parsed.params.size());
		Pair<JavaType, String> pair = parsed.params.get(0);
		assertEquals("param name", "String", pair.first.typeName);
		assertEquals("param name", "name", pair.second);
	}

	@Test(expected = ParseException.class)
	public void duplicateName() throws ParseException {
		String methodSignature = "void something(String value, int value)";
		MethodSignature parsed = new MethodSignature(methodSignature);
	}
	
	@Test
	public void aFailedVararg() throws ParseException {
		String methodSignature = "void debug(String message, Object...data)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("Object", parsed.varargType.typeName);
		assertEquals("data", parsed.varargName);
	}

	@Test(expected = ParseException.class)
	public void extraParentheses() throws ParseException {
		String methodSignature = "startBlock(String one, String two()";
		MethodSignature parsed = new MethodSignature(methodSignature);
	}

	@Test
	public void parameterOrder() throws ParseException {
		String methodSignature = "between(int atLeast, int atMost, String somethingElse, java.lang.Boolean _paramX)";
		MethodSignature parsed = new MethodSignature(methodSignature);

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
	public void allowsNoReturnType() throws ParseException {
		String methodSignature = "debug(String message)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("no return type", null, parsed.returnType);
	}

	@Test
	public void emptyTest() throws ParseException {
		String methodSignature = "method()";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("no params", 0, parsed.params.size());
	}

	@Test
	public void emptyWithReturnType() throws ParseException {
		String methodSignature = "String method()";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("no params", 0, parsed.params.size());
	}

	@Test
	public void singleTypeParameterInFront() throws ParseException {
		String methodSignature = "List<String> method()";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("no params", 0, parsed.params.size());
		assertEquals("type with type parameter", "List<String>", parsed.returnType.toString());
	}

	@Test
	public void multipleTypeParameterInFront() throws ParseException {
		String methodSignature = "Map<String, Integer> method()";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("no params", 0, parsed.params.size());
		assertEquals("type with type parameter", "Map<String, Integer>", parsed.returnType.toString());
	}

	@Test
	public void singleTypeParameterInside() throws ParseException {
		String methodSignature = "void method(List<String> param)";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "List<String>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void multipleTypeParameterInside() throws ParseException {
		String methodSignature = "void method(Map<String, Integer> param)";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String, Integer>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test
	public void nestedTypeParameters() throws ParseException {
		String methodSignature = "void method(Map<String, Set<String>> param)";
		MethodSignature parsed = new MethodSignature(methodSignature);

		assertEquals("single param", 1, parsed.params.size());
		assertEquals("parameter type", "Map<String, Set<String>>", parsed.params.get(0).first.toString());
		assertEquals("parameter type", "param", parsed.params.get(0).second);
	}

	@Test(expected=ValidationException.class)
	public void keywordsAreInvalid() throws Exception {
		String methodSignature = "void continue(int x)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		parsed.validate();
	}

	@Test
	public void arrayTypeAsReturn() throws ParseException {
		String methodSignature = "int[] method()";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 1, parsed.returnType.arrayDepth);
	}

	@Test
	public void arrayTypeAsParameter() throws ParseException {
		String methodSignature = "void method(int[] p1, String p2)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
		assertEquals("not array type", 0, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void multidimensionalArrayTypeAsParameter() throws ParseException {
		String methodSignature = "void method(int[] [] p1, String p2)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 2, parsed.params.get(0).first.arrayDepth);
		assertEquals("not array type", 0, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void arrayTypeAsParameterAfterIdentifier() throws ParseException {
		String methodSignature = "void method(int p1, String p2[])";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("not array type", 0, parsed.params.get(0).first.arrayDepth);
		assertEquals("array type", 1, parsed.params.get(1).first.arrayDepth);
	}

	@Test
	public void arrayTypeAsTypeParameter() throws ParseException {
		String methodSignature = "void method(List<byte[]> list)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("not array type", 0, parsed.params.get(0).first.arrayDepth);
		assertEquals("array type", 1, parsed.params.get(0).first.typeParameters.get(0).arrayDepth);
	}

	@Test(expected=ParseException.class)
	public void arrayTypesWithTypeParametersNotOk() throws ParseException {
		String methodSignature = "void method(List[]<String> p1)";
		new MethodSignature(methodSignature);
	}

	@Test
	public void arrayTypesWithTypeParametersOk1() throws ParseException {
		String methodSignature = "void method(List<String>[] p1)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
	}

	@Test
	public void arrayTypesWithTypeParametersOk2() throws ParseException {
		String methodSignature = "void method(List<String> p1[])";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 1, parsed.params.get(0).first.arrayDepth);
	}

	@Test
	public void multidimensionalArrayTypesWithTypeParameters() throws ParseException {
		String methodSignature = "void method(List<String>[][][] p1)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("string type", "List", parsed.params.get(0).first.typeName);
		assertEquals("string type", "String", parsed.params.get(0).first.typeParameters.get(0).typeName);
		assertEquals("array type", 3, parsed.params.get(0).first.arrayDepth);
	}

	@Test(expected=ParseException.class)
	public void duplicateArrayIndexers() throws ParseException {
		String methodSignature = "void method(int[] p1[])";
		new MethodSignature(methodSignature);
	}

	@Test(expected=ParseException.class)
	public void duplicateArrayIndexerWithTypeParam() throws ParseException {
		String methodSignature = "void method(List<String>[] array[])";
		new MethodSignature(methodSignature);
	}

	@Test
	public void multiDimensionalArray() throws ParseException {
		String methodSignature = "void method(int[][] p1)";
		MethodSignature parsed = new MethodSignature(methodSignature);
		assertEquals("array type", 2, parsed.params.get(0).first.arrayDepth);
	}

	@Test(expected=ParseException.class)
	public void multiDimensionalDuplicate() throws ParseException {
		String methodSignature = "void method(int[][] p1[])";
		new MethodSignature(methodSignature);
	}

	@Test
	public void genericReturnType() throws ParseException {
		MethodSignature parsed = new MethodSignature("List<String> method()");
		assertEquals(1, parsed.returnType.typeParameters.size());
		assertEquals("List", parsed.returnType.typeName);
		assertEquals("String", parsed.returnType.typeParameters.get(0).typeName);
	}

	@Test
	public void genericReturnCompilerEquivalence() throws ParseException {
		MethodSignature p1 = new MethodSignature("List<String> method()");
		MethodSignature p2 = new MethodSignature("List<Integer> method()");
		MethodSignature p3 = new MethodSignature("List method()");

		assertTrue(p1.compilerEquivalent(p2));
		assertTrue(p1.compilerEquivalent(p3));
		assertTrue(p2.compilerEquivalent(p3));
	}

	@Test
	public void toStringTests() throws ParseException {
		testMethodToString(true, "void method()");
		testMethodToString(true, "void method(String a, int b)");
		testMethodToString(true, "List<String> method()");
		testMethodToString(true, "void method(int...vararg)");
		testMethodToString(true, "void method(int[]...vararg)");
		testMethodToString(true, "void method(List<String> stuff)");
		testMethodToString(true, "void method(List<Integer> numbers, String...vararg)");
		testMethodToString(true, "void method(List<String>...lists)");
	}

	@Test
	public void testFQCNReturn() throws ParseException {
		MethodSignature parsed = new MethodSignature("java.lang.String method()");
		assertEquals("java.lang.String", parsed.returnType.typeName);
		assertEquals(0, parsed.returnType.arrayDepth);
	}

	@Test
	public void testFQCNReturnArray() throws ParseException {
		MethodSignature parsed = new MethodSignature("java.lang.String[] method()");
		assertEquals("java.lang.String", parsed.returnType.typeName);
		assertEquals(1, parsed.returnType.arrayDepth);
	}

	@Test
	public void validWildcard() throws ParseException {
		MethodSignature signature = new MethodSignature("void method(Class<?> clazz)");
		assertEquals("Class", signature.params.get(0).first.typeName);
		assertEquals("?", signature.params.get(0).first.typeParameters.get(0).typeName);
	}

	@Test(expected=ParseException.class)
	public void invalidDoubleWildcard() throws ParseException {
		new MethodSignature("void method(Class<??> clazz)");
	}

	@Test
	public void validDoubleWildcard() throws ParseException {
		MethodSignature signature = new MethodSignature("void method(Map<?, ?> map)");

		JavaType param = signature.params.get(0).first;
		assertEquals("Map", param.typeName);

		assertEquals(2, param.typeParameters.size());
		assertEquals("?", param.typeParameters.get(0).typeName);
		assertEquals("?", param.typeParameters.get(1).typeName);
	}

	@Test
	public void wildcardReturnType() throws ParseException {
		MethodSignature signature = new MethodSignature("Class<?> method()");

		assertEquals("Class", signature.returnType.typeName);
		assertEquals("?", signature.returnType.typeParameters.get(0).typeName);
	}

	@Test
	public void wildcardVarargs() throws ParseException {
		MethodSignature signature = new MethodSignature("void method(Class<?>...classes)");

		assertEquals("Class", signature.varargType.typeName);
		assertEquals("?", signature.varargType.typeParameters.get(0).typeName);
	}

	@Test
	public void testWildcardArray() throws ParseException {
		MethodSignature signature = new MethodSignature("method(Class<?>[] classes)");
		JavaType param = signature.params.get(0).first;

		assertEquals("Class", param.typeName);
		assertEquals("?", param.typeParameters.get(0).typeName);
		assertEquals(1, param.arrayDepth);
	}

	@Test
	public void methodEqualityTests() throws ParseException {

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

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	private static void testMethodEquality(boolean expected, String method1, String method2)  throws ParseException {
		MethodSignature m1 = new MethodSignature(method1);
		MethodSignature m2 = new MethodSignature(method2);

		if (expected) {
			assertTrue("expected equal methods (<"+method1+"> <"+method2+">)", m1.compilerEquivalent(m2));
		} else {
			assertFalse("expected unequal methods (<"+method1+"> <"+method2+">)", m1.compilerEquivalent(m2));
		}
	}

	private static void testMethodToString(boolean expected, String method) throws ParseException {
		MethodSignature signature = new MethodSignature(method);

		if (expected) {
			assertEquals(method, signature.toString());
		} else {
			assertNotEquals(method, signature.toString());
		}
	}
}