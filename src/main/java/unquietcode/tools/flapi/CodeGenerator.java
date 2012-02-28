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
	Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	
	public JCodeModel generateCodeModel(DescriptorHelper descriptorHelper) {
		// create the package which will hold everything
		String packageName = descriptorHelper.packageName;
		if (packageName == null) { packageName = ""; }
		thePackage = model._package(packageName);

		// create the Builder interface
		JDefinedClass builder = generateBuilder(thePackage, "Descriptor");
		
		// the top level interface
		JDefinedClass topLevel = getOrCreateInterface(thePackage, "Descriptor" + "Interfaces");

		// process all blocks		
		for (BlockData block : descriptorHelper.blocks) {
			processBlock(topLevel, builder, block);

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
	
	private JDefinedClass generateBuilder(JPackage _package, String name) {
		JDefinedClass builder = getOrCreateInterface(_package, name + "Builder");
		builder.generify("_ReturnValue");

		// add build method
		builder.method(0, model.VOID, "build");
		
		// get return type
		JClass rType = model.ref(MethodInterface.class);
		rType = rType.narrow(builder.typeParams()[0]);
		
		// add starting block method
		JMethod startBlock = builder.method(0, rType, "startBlock");
		startBlock.param(String.class, "blockName");
		startBlock.param(String.class, "methodSignature");
		return builder;
	}
	
	private void processBlock(JDefinedClass parentClass, JDefinedClass returnClass, BlockData block) {
		// process the combinations of methods which will define the child interfaces
		int counter = 1;
		Map<String, Integer> nameMap = new HashMap<String, Integer>();
		
		// get the complete list of method names
		for (MethodData method : block.methods) {
			String methodName = getMethodName(method.methodSignature);
			nameMap.put(methodName, counter++);
		}
		
		// get the combinations of interfaces
		Set<Set<MethodData>> combinations = next(new HashSet<MethodData>(block.methods));
		
		// for each combination, add an interface
		for (Set<MethodData> combination : combinations) {
			// skip the empty one (we will end up modifying it accidentally otherwise)
			if (combination.isEmpty()) { continue; }
			
			// create a new interface 
			JDefinedClass _interface;
			String iName = getInterfaceName(block.blockName, combination, nameMap);
			_interface = getOrCreateInterface(parentClass, iName);

			// extends parent with type of itself
			_interface._implements(returnClass.narrow(_interface));
			
			// add the methods
			for (MethodData method : combination) {
				JClass rType;
				
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
					rType = getOrCreateInterface(parentClass, returnType);

					// if we've gone terminal, then narrow to self
					if (returnType.equals(block.blockName)) {
						rType = rType.narrow(rType);
					}
				}
				
				JMethod m = _interface.method(0, rType, getMethodName(method.methodSignature));
				createMethod(m, method);
			}
			
			// now do any child blocks
			for (BlockData child : block.blocks) {
				System.err.println(child.blockName);
			}
		}
		
	}
	
	private JDefinedClass getOrCreateInterface(JDefinedClass parentClass, String iName) {
		JDefinedClass _interface = interfaces.get(iName);
		if (_interface == null) {
			try {
				_interface = parentClass._interface(iName);
				interfaces.put(iName, _interface);
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		return _interface;
	}
	
	private JDefinedClass getOrCreateInterface(JPackage _package, String iName) {
		JDefinedClass _interface = interfaces.get(iName);
		if (_interface == null) {
			try {
				_interface = _package._interface(iName);
				interfaces.put(iName, _interface);
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		return _interface;
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
			if (paramString.isEmpty()) { continue; } // no params

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
