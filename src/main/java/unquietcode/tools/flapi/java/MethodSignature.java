/*********************************************************************
 Copyright 2014 the Flapi authors

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

package unquietcode.tools.flapi.java;

import com.google.common.collect.ImmutableSet;
import unquietcode.tools.flapi.Pair;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 03-03-2012
 */
public class MethodSignature implements Comparable<MethodSignature> {
	public final String methodName;
	public final JavaType returnType;
	public final List<Pair<JavaType, String>> params = new ArrayList<Pair<JavaType, String>>();
	public final String varargName;
	public final JavaType varargType;
	public final String originalSignature;

	// working
	private char[] signature;
	private int cur = 0;

	public MethodSignature(String methodSignature) throws ParseException {
		if (methodSignature == null) {
			throw new IllegalArgumentException("method signature cannot be null");
		}

		methodSignature = methodSignature.trim();
		originalSignature = methodSignature;
		signature = originalSignature.toCharArray();
		boolean seenVarargs = false;
		JavaType _varargType = null;
		String _varargName = null;
		JavaType _returnType;
		String _methodName;

		// RETURN TYPE
		_returnType = matchType();
		match(WS, -1);

		// METHOD NAME
		try {
			_methodName = matchIdentifier();
		} catch (ParseException ex) {
			// means the type was the method name, which means no return type
			_methodName = _returnType.typeName;
			_returnType = null;
		}
		match(WS, -1);

		// LPAREN
		match(LP, 1);
		match(WS, -1);

		// parameters
		while (true) {

			// PARAMETER TYPE
			JavaType pType;
			try {
				pType = matchType();
				match(WS, -1);
			} catch (ParseException ex) {
				break; // no params
			}

			// VARARGS
			try {
				match(DOT, 3);
				match(WS, -1);
				seenVarargs = true;
			} catch (ParseException ex) {
				// nothing
			}

			String pName = matchIdentifier();
			match(WS, -1);

			if (peek(LB, 1)) {
				if (pType.arrayDepth > 0) {
					throwGeneralException("duplicate array declaration");
				}
				int arrayDepth = matchArray();
				pType = new JavaType(pType, arrayDepth);
			}

			if (seenVarargs) {
				_varargType = pType;
				_varargName = pName;
				break;  // varargs are always last
			} else {
				params.add(new Pair<JavaType, String>(pType, pName));
			}

			// COMMA
			if (peek(COMMA, 1)) {
				match(COMMA, 1);
				match(WS, -1);
			} else {
				match(WS, -1);
				break;
			}
		}

		// RPAREN
		match(RP, 1);

		// check for duplicate parameter names
		checkForDuplicateParameters();

		// set variables
		returnType = _returnType;
		methodName = _methodName;
		varargName = _varargName;
		varargType = _varargType;

		// clean up working set
		signature = null;
	}

	private void checkForDuplicateParameters() throws ParseException {
		Set<String> seen = new HashSet<String>();

		for (Pair<JavaType, String> param : params) {
			if (seen.contains(param.second)) {
				throw new ParseException("Duplicate parameter name: '"+param.second+"'.");
			} else {
				seen.add(param.second);
			}
		}
	}

	private JavaType matchType() throws ParseException {
		String typeName;
		int arrayDepth = 0;
		List<JavaType> typeParameters = new ArrayList<JavaType>();

		match(WS, -1);
		typeName = matchIdentifier();
		match(WS, -1);

		if (peek(LB, 1)) {
			arrayDepth = matchArray();
		}

		if (peek(LAB, 1)) {
			if (arrayDepth > 0) {
				throwGeneralException("invalid array declaration");
			}
			match(LAB, 1);

			while (true) {
				match(WS, -1);

				// wildcard
				if (peek(QM, 1)) {
					match(QM, 1);
					typeParameters.add(JavaType.wildcard());
				}

				// normal
				else {
					typeParameters.add(matchType());
				}

				match(WS, -1);

				if (peek(COMMA, 1)) {
					match(COMMA, 1);
				} else {
					break;
				}
			}

			match(RAB, 1);
		}

		match(WS, -1);

		if (peek(LB, 1)) {
			arrayDepth = matchArray();
		}

		return new JavaType(typeName, arrayDepth, typeParameters);
	}

	private int matchArray() throws ParseException {
		int depth = 0;
		match(WS, -1);

		while (peek(LB, 1)) {
			match(LB, 1);
			match(WS, -1);
			match(RB, 1);
			match(WS, -1);

			++depth;
		}

		return depth;
	}

	private String matchIdentifier() throws ParseException {
		StringBuilder sb = new StringBuilder();
		match(WS, -1);
		sb.append(match(ID_START, 1)).append(match(ID, -1));

		while (true) {
			// vararg
			if (peek(DOT, 3)) {
				break;
			}

			// partial identifier
			if (peek(DOT, 1)) {
				match(DOT, 1);
				sb.append('.');

				sb.append(match(ID_START, 1)).append(match(ID, -1));
			} else {
				break;
			}
		}

		return sb.toString();
	}

	private String match(Set<Character> chars, int count) throws ParseException {
		StringBuilder sb = new StringBuilder();

		while (count != 0) {
			if (cur >= signature.length) {
				throwGeneralException("Unexpected EOF.");
			}

			if (chars.contains(signature[cur])) {
				sb.append(signature[cur]);
			} else {
				if (count < 0) { break; }
				else { throwUnexpectedCharException(chars); }
			}

			++cur;
			--count;
		}

		return sb.toString();
	}

	private boolean peek(Set <Character> chars, int count) {
		int lcur = cur;

		while (count != 0) {
			if (chars.contains(signature[lcur])) {
				// nothing
			} else {
				if (count < 0) { break; }
				else { return false; }
			}

			++lcur;
			--count;
		}

		return true;
	}

	private void throwGeneralException(String message) throws ParseException {
		StringBuilder sb = new StringBuilder();
		sb.append(message)
		  .append(" (method signature is [ '").append(new String(signature)).append("' ])");

		throw new ParseException(sb.toString());
	}

	private void throwUnexpectedCharException(Set<Character> chars) throws ParseException {
		StringBuilder sb = new StringBuilder();
		boolean first = true;

		sb.append("Expected to find character in ");

		if (chars == ID_START) {
			sb.append("[A-Za-z0-9_$]");
		} else if (chars == ID) {
			sb.append("[A-Za-z_$]");
		} else {
			sb.append("{");

			for (Character aChar : chars) {
				if (!first) {
					sb.append(", ");
				} else {
					first = false;
				}

				sb.append(aChar);
			}

			sb.append("}");
		}

		sb.append(" but was ");

		if (cur >= signature.length) {
			sb.append("empty");
		} else {
			sb.append("'").append(signature[cur]).append("'");
		}

		sb.append(" (method signature is [ '").append(new String(signature)).append("' ]).");
		throw new ParseException(sb.toString());
	}

	public static class ValidationException extends Exception {
		ValidationException(String message) {
			super(message);
		}
	}

	private static final Set<Character> LP 		= ImmutableSet.of('(');
	private static final Set<Character> RP 		= ImmutableSet.of(')');
	private static final Set<Character> WS 		= ImmutableSet.of('\t', ' ', '\n', '\r');
	private static final Set<Character> COMMA 	= ImmutableSet.of(',');
	private static final Set<Character> DOT 	= ImmutableSet.of('.');
	private static final Set<Character> LAB 	= ImmutableSet.of('<');
	private static final Set<Character> RAB 	= ImmutableSet.of('>');
	private static final Set<Character> LB 		= ImmutableSet.of('[');
	private static final Set<Character> RB 		= ImmutableSet.of(']');
	private static final Set<Character> QM 		= ImmutableSet.of('?');

	private static final Set<Character> ID_START = ImmutableSet.of(
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
		'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h',
		'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y',
		'z','$','_'
	);

	private static final Set<Character> ID = ImmutableSet.<Character>builder()
		.addAll(ID_START)
		.add('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
	.build();

	//-------------------------------------------------------------------------------------------//

	public boolean compilerEquivalent(MethodSignature other) {

		// same name
		if (!methodName.equals(other.methodName)) { return false; }

		// synthesize new parameter arrays, accounting for varargs

		List<Pair<JavaType, String>> newParams = new ArrayList<Pair<JavaType, String>>(params);

		if (varargType != null) {
			newParams.add(new Pair<JavaType, String>(new JavaType(varargType, 1), varargName));
		}

		List<Pair<JavaType, String>> newOtherParams = new ArrayList<Pair<JavaType, String>>(other.params);

		if (other.varargType != null) {
			newOtherParams.add(new Pair<JavaType, String>(new JavaType(other.varargType, 1), other.varargName));
		}

		// parameter size
		if (newParams.size() != newOtherParams.size()) {
			return false;
		}

		// parameter types
		for (int i=0; i < newParams.size(); ++i) {
			Pair<JavaType,String> p1 = newParams.get(i);
			Pair<JavaType,String> p2 = newOtherParams.get(i);

			if (!p1.first.compilerEquivalent(p2.first)) { return false; }
		}

		// and same return type

		if (returnType == null && other.returnType != null) {
			if (!other.returnType.typeName.equals("void")) {
				return false;
			}
		}

		if (returnType != null && other.returnType == null) {
			if (!returnType.typeName.equals("void")) {
				return false;
			}
		}

		if (returnType != null && other.returnType != null) {
			if (!returnType.typeName.equals(other.returnType.typeName)) {
				return false;
			}
		}

		// otherwise, equal (probably)
		return true;
	}

	public void validate() throws ValidationException {

		// check that method name is legitimate
		if (!SourceVersion.isName(methodName)) {
			throw new ValidationException(
				"Invalid method name: '"+ originalSignature +"'."
			);
		}

		// check that parameters are also valid names
		for (Pair<JavaType, String> param : params) {
			if (!SourceVersion.isName(param.second)) {
				throw new ValidationException(
					"Invalid parameter name '"+param.second+"' in method '"+originalSignature+"'."
				);
			}
		}

		// check vararg name
		if (varargName != null) {
			if (!SourceVersion.isName(varargName)) {
				throw new ValidationException(
					"Invalid vararg parameter name '"+varargName+"' in method '"+originalSignature+"'."
				);
			}
		}
	}

	// return the number of parameters, accounting for varargs
	public int parameterCount() {
		return params.size() + (varargType != null ? 1 : 0);
	}

	@Override
	public String toString() {
		StringBuilder signature = new StringBuilder();

		// method return type
		if (returnType != null) {
			signature.append(returnType.toString()).append(" ");
		} else {
			signature.append("void ");
		}

		// method name
		signature.append(methodName).append("(");

		// parameters
		for (int i = 0; i < params.size(); i++) {
			Pair<JavaType, String> param = params.get(i);
			if (i != 0) { signature.append(", "); }

			// parameter type
			signature.append(param.first.toString());

			// parameter name
			signature.append(" ").append(param.second);
		}

		// vararg parameter
		if (varargType != null) {
			if (params.size() > 0) { signature.append(", "); }

			signature.append(varargType.toString()).append("...").append(varargName);
		}

		signature.append(")");
		return signature.toString();
	}

	@Override
	public int compareTo(MethodSignature other) {
		return this.toString().compareTo(other.toString());
	}
}