package unquietcode.tools.flapi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-03-2012
 */
public class MethodParser {
	public String methodName;
	public String returnType;
	public Map<String, String> params = new HashMap<String, String>();
	public String varargName;
	public String varargType;

	public MethodParser(String methodSignature) {
		parse(methodSignature);
	}
	
	private void parse(String methodSignature) {
		if (methodSignature == null) {
			throw new RuntimeException("null signature");
		}

		methodSignature = methodSignature.trim();
		int lParen = -1;
		int rParen = -1;

		// LPAREN
		if ((lParen = methodSignature.indexOf("(")) == -1) {
			throw new RuntimeException("expected a left paren");
		}

		// RPAREN
		if ((rParen = methodSignature.indexOf(")")) == -1) {
			throw new RuntimeException("expected a right paren");
		}

		// RETURN_TYPE
		// METHOD_IDENTIFIER

		String[] front = methodSignature.substring(0, lParen).split("\\s+");
		if (front.length < 1) {
			throw new ParseException("Expected at least a method name.");
		} else if (front.length == 1) {
			returnType = null;
			methodName = front[0].trim();
		} else if (front.length == 2) {
			returnType = front[0].trim();
			methodName = front[1].trim();
		} else {
			throw new ParseException("Expected a name and return type only.");
		}
		
		

		

		int start = lParen+1;
		int comma = -1;
		boolean seenVarargs = false;

		// (XXX,XXX)
		do {
			if (seenVarargs) {
				throw new RuntimeException("Varargs should be last");
			}

			comma = methodSignature.indexOf(",", start);
			int end = comma != -1 ? comma : rParen;
			String pair = methodSignature.substring(start, end).trim();
			
			// is empty?
			if (pair.trim().isEmpty()) {
				break;
			}
			
			// is varargs (XX...XX)
			int dots = -1;
			if ((dots = pair.indexOf("...")) != -1) {
				seenVarargs = true;
				varargType = pair.substring(0, dots).trim();
				varargName = pair.substring(dots+3).trim();

				if (varargName.isEmpty() || varargType.isEmpty()) {
					throw new RuntimeException("unexpected vararg string");
				}
			} else {
				String[] parts = pair.split("\\s+");
				if (parts.length != 2) {
					throw new RuntimeException("expected type and name pair");
				}

				String name = parts[1].trim();
				String type = parts[0].trim();

				// no duplicate names allowed
				if (params.containsKey(name)) {
					throw new ParseException("Duplicate parameter name ("+name+").");
				}

				params.put(name, type);
			}

			start = end+1;
		} while (comma != -1);	
	}

	public static class ParseException extends RuntimeException {
		ParseException(String message) {
			super(message);
		}
		
		ParseException(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
	
}
