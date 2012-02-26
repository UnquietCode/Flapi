package unquietcode.tools.flapi;

import java.io.IOException;
import java.util.*;

import com.sun.codemodel.internal.*;
import com.sun.codemodel.internal.writer.SingleStreamCodeWriter;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * @author Benjamin Fagin
 * @version 02-15-2012
 */
public class CodeGenerator {
	private static final int MAX_METHODS = Integer.MAX_VALUE - 1000;
	
	/*
			for every block
				aggregate




			case "at most"
			case "at least"
			case "any"
			case "once"

			really just: atMost+atLeast or any


			once implies that we have a combination of 'at most = 1' and 'at-least = 1'



	 */

	
	JCodeModel model = new JCodeModel();	
	JPackage thePackage;
	
	public JCodeModel generateCodeModel(DescriptorHelper descriptorHelper) {
		// create the package which will hold everything
		String packageName = descriptorHelper.packageName;
		if (packageName == null) { packageName = ""; }
		thePackage = model._package(packageName);

		// the top level interface
		String name = "Descriptor";
		JDefinedClass topLevel;
		
		try {
			topLevel = thePackage._interface(name + "Interfaces");    // TODO get name
			topLevel.generify("_ReturnValue");
		} catch (JClassAlreadyExistsException impossible) {
			throw new RuntimeException("Impossible!");
		}
		
		// add build method
		topLevel.method(0, model.VOID, "build");
		
		// add first block method
		JMethod startBlock = topLevel.method(0, MethodInterface.class, "startBlock");
		startBlock.param(String.class, "blockName");
		startBlock.param(String.class, "methodSignature");
		
		
		// process all blocks		
		for (BlockData block : descriptorHelper.blocks) {
			//JDefinedClass blockClass = processBlock(topLevel, block);
			processBlock(topLevel, block);
			// TODO should be void?

			// the block's constructor will be added to the top level class
//			if (topLevel != null) {
//				topLevel.method(Modifier.STATIC + )
//			} else {
//				topLevel = blockClass;				
//			}
			
		}
		
		// the constructor for the first block goes in the top level
		// so maybe process constructors ahead each time
		// but top level block also needs a 'build' added to the top level interface, so....


		ByteOutputStream stream = new ByteOutputStream();
		SingleStreamCodeWriter writer = new SingleStreamCodeWriter(stream);
		try {
			model.build(writer);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println(stream.toString());

		return null;
	}
	
	private void processBlock(JDefinedClass parentClass, BlockData block) {
		// process the combinations of methods which will define the child interfaces

		int counter = 1;
		Set<String> methodNames = new HashSet<String>();
		Map<String, Integer> nameMap = new HashMap<String, Integer>();
		Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
		
		// get the complete list of method names
		for (MethodData method : block.methods) {
			String methodName = getMethodName(method.methodSignature);
			
			// make sure we haven't seen this already
			if (methodNames.contains(methodName)) {
				throw new RuntimeException("Duplicate methods on  the same block: " + methodName);
			}
			
			methodNames.add(methodName);
			nameMap.put(methodName, counter++);
		}
		
		// get the combinations of interfaces
		Set<Set<MethodData>> combinations = next(new HashSet<MethodData>(block.methods));
		
		// for each combination, add an interface
		for (Set<MethodData> combination : combinations) {
			if (combination.isEmpty()) {
				try {
					JDefinedClass cls = parentClass._interface(block.blockName);
					cls.generify("_ReturnType");
					interfaces.put(block.blockName, cls);
				} catch (JClassAlreadyExistsException ex) {
					throw new RuntimeException(ex);
				}
				
				continue;
			}
			
			// create a new interface 
			JDefinedClass _interface;
			try {
				String iName = getInterfaceName(block.blockName, combination, nameMap);
				_interface = interfaces.get(iName);
				if (_interface == null) {
					_interface = parentClass._interface(iName);
					interfaces.put(iName, _interface);
				}
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}

			// extends parent with type of itself
			_interface._implements(parentClass.narrow(_interface));
			
			// add the methods
			for (MethodData method : combination) {
				JDefinedClass rType;
				
				if (method.isRequired()) {
					rType = _interface;
				} else {
					Set<MethodData> minusMethod = new HashSet<MethodData>(combination);
					minusMethod.remove(method);
					
					if (method.maxOccurrances > 1) {
						MethodData m = method.copy();						
						m.maxOccurrances = m.maxOccurrances - 1;
						minusMethod.add(m);
					}
					
					String returnType = getInterfaceName(block.blockName, minusMethod, nameMap);
					rType = interfaces.get(returnType);
					if (rType == null ) {
						try {
							rType = parentClass._interface(returnType);
						} catch (JClassAlreadyExistsException ex) {
							throw new RuntimeException(ex);
						}

						interfaces.put(returnType, rType);
					}
				}
				
				JMethod m = _interface.method(0, rType, getMethodName(method.methodSignature));
				createMethod(m, method);
			}
			
//			Set<Set<MethodData>> methods = next(new HashSet<MethodData>(combination));
//			for (Set<MethodData> set : methods) {
//				String name = getInterfaceName(_interface, set, nameMap);
//				
//			}
		}
		
	}
	
	private void createMethod(JMethod _method, MethodData method) {
		// get the parameter portion of the method string
		int lParen =  method.methodSignature.indexOf("(");
		int rParen = method.methodSignature.indexOf(")");
		if (lParen < 0 || rParen < 0) {
			throw new RuntimeException("Invalid method signature: " + method.methodSignature);
		}
		
		// get the individual parameters
		Map<String, JType> params = new HashMap<String, JType>();
		String[] paramStrings = method.methodSignature.substring(lParen+1, rParen).split(",");
		
		for (String paramString : paramStrings) {
			paramString = paramString.trim();
		    int space = paramString.indexOf(" ");
			String pType = paramString.substring(0, space);
			String pName = paramString.substring(space).trim();
			
			JType clazz;
			
			if ("boolean".equals(pType)) {
				clazz = model.BOOLEAN;
			} else if ("int".equals(pType)) {
				clazz = model.INT;
			} else if ("byte".equals(pType)) {
				clazz = model.BYTE;
			} else if ("short".equals(pType)) {
				clazz = model.SHORT;
			} else if ("float".equals(pType)) {
				clazz = model.FLOAT;
			} else if ("double".equals(pType)) {
				clazz = model.DOUBLE;
			} else {
				// expect fully qualified class name
				clazz = model._getClass(pType);
				
				// try java.lang package
				if (clazz == null) {
					clazz = model.ref("java.lang."+pType);
				}

				// fail
				if (clazz == null) {
					throw new RuntimeException("Could not find type reference for type name '"+ pType +"'.");
				}
			}

			params.put(pName, clazz);
		}
		
		// add the parameters
		for (Map.Entry<String, JType> param : params.entrySet()) {
			_method.param(param.getValue(), param.getKey());
		}
	}
	
	private String getInterfaceName(String className, Set<MethodData> methods, Map<String, Integer> nameMap) {
		StringBuilder name = new StringBuilder();
		name.append(className);

		// TODO change to mapped names after debugging
		for (MethodData method : methods) {
			name.append("_").append(getMethodName(method.methodSignature));
			//.append(nameMap.get(getMethodName(method.methodSignature)))

			if (method.maxOccurrances > 1) {
				name.append("x").append(method.maxOccurrances);
			}
		}
		
		return name.toString();
	}

	static Set<Set<MethodData>> next(Set<MethodData> methods) {
		Set<Set<MethodData>> combinations = new HashSet<Set<MethodData>>();
		Stack<Set<MethodData>> stack = new Stack<Set<MethodData>>();
		stack.push(methods);

		while (!stack.isEmpty()) {
			Set<MethodData> set = stack.pop();
			combinations.add(set);

			for (MethodData method : set) {
				Set<MethodData> next = new HashSet<MethodData>(set);
				boolean changed = false;

				// only remove if not required
				if (!method.isRequired()) {
					next.remove(method);
					changed = true;
				}

				// if we can afford to lose one occurrence then do it
				if (method.maxOccurrances > 1) {
					MethodData m = method.copy();

					m.maxOccurrances = m.maxOccurrances - 1;
					next.add(m);
					changed = true;
				}

				// only push if we've made useful changes
				if (changed) {
					stack.push(next);
				}
			}
		}

		return combinations;
	}



	// 1-based!
	private static List<Integer[]> getCombintionSequences(int items) {
		// TODO generate all of the sequences in nCr
		return null;
	}
	
	private String getMethodName(String methodSignature) {
		int lParen = methodSignature.indexOf("(");
		if (lParen < 0) {
			throw new RuntimeException("Invalid method signature: " + methodSignature);
		}
		
		String name = methodSignature.substring(0, lParen).trim();
		
		if (!isValidMethodName(name)) {
			throw new RuntimeException("Invalid method name: " + name);
		}
		
		return name;
	}
	
	private boolean isValidMethodName(String name) {
		name = name.trim();
		
		if (name.isEmpty()) { return false; }
		if (name.contains(" ")) { return false; }
		
		
		return true;
	}
}
