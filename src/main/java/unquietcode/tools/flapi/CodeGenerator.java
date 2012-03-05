package unquietcode.tools.flapi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.sun.codemodel.*;
import com.sun.codemodel.writer.SingleStreamCodeWriter;
import com.sun.codemodel.writer.FileCodeWriter;

/**
 * @author Benjamin Fagin
 * @version 02-15-2012
 */
public class CodeGenerator {
	private static final int MAX_METHODS = Integer.MAX_VALUE - 1000;
	private static final String RETURN_TYPE_PARAM = "_ReturnValue";
	
	JCodeModel model = new JCodeModel();	
	JPackage thePackage;
	Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();

	public JCodeModel generateCodeModel(DescriptorHelper descriptorHelper) {
		// create the package which will hold everything
		String packageName = descriptorHelper.packageName;
		if (packageName == null) { packageName = ""; }
		thePackage = model._package(packageName);
		
		// check for possible collisions
		checkForCollisions(descriptorHelper.blocks);
		
		// create the top level builder class
		JDefinedClass topLevel = getOrCreateClass(thePackage, descriptorHelper.descriptorName);

		// generate the *Generator class
		generateGeneratorClass(thePackage, descriptorHelper);
		
		// process all blocks		
		for (BlockData block : descriptorHelper.blocks) {
			processBlock(thePackage, block);
		}
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		SingleStreamCodeWriter writer = new SingleStreamCodeWriter(stream);
		try {
			model.build(writer);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println(stream.toString());


		// TODO also write files temporarily
		try {
			FileCodeWriter fileWriter = new FileCodeWriter(new File("/Users/bfagin/Desktop/lmbuilder"), false);
			model.build(fileWriter);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		return null;
	}

	JDefinedClass generateGeneratorClass(JPackage thePackage, DescriptorHelper helper) {
		// figure out the top level interface name from the methods
		Set<MethodData> dynamicMethods = new HashSet<MethodData>();
		for (MethodData method : helper.methods) {
			if (!method.isRequired()) {
				dynamicMethods.add(method);
			}
		}
		String topName = getGeneratedName(helper.descriptorName, dynamicMethods);
		JDefinedClass topClass = getOrCreateClass(thePackage, topName);
		JDefinedClass generator = getOrCreateClass(thePackage, helper.descriptorName+"Generator");
		
		// add the constructor methods
		
		JMethod createWithString = generator.method(JMod.PUBLIC+JMod.STATIC, topClass, "create");
		createWithString.param(model.ref(String.class), "name");

		// TODO return type and argument check

		createWithString.body()._return(null);

		JMethod create = generator.method(JMod.PUBLIC+JMod.STATIC, topClass, "create");
		create.body()._return(JExpr.invoke(createWithString).arg("create"));
		
		return generator;
	}
	
	
	private void checkForCollisions(Collection<BlockData> blocks) {
		_checkForCollisions(blocks, new HashSet<String>());
	}

	private void _checkForCollisions(Collection<BlockData> blocks, Set<String> blockNames) {
		for (BlockData block : blocks) {
			if (blockNames.contains(block.blockName)) {
				throw new RuntimeException("Duplicate blocks named '"+block.blockName+"'.");
			} else {
				blockNames.add(block.blockName);
			}

			Set<String> methodSignatures = new HashSet<String>();
			for (MethodData method : block.methods) {
				if (methodSignatures.contains(method.methodSignature)) {
					throw new RuntimeException("Duplicate methods '"+method.methodSignature+"'.");
				} else {
					methodSignatures.add(method.methodSignature);
				}	
			}

			_checkForCollisions(block.blocks, blockNames);
		}
	}


	/*
		Generate the *Builder interface.
		This method makes the class and adds the required methods.
		Then it is passed along and decorated with the any/only methods.
	 */
	private JDefinedClass generateBuilderInterface(JPackage _package, String name) {
		JDefinedClass builder = getOrCreateInterface(_package, name+"Builder");
		builder.generify(RETURN_TYPE_PARAM);

		// add build method
		//builder.method(0, model.VOID, "build");

		return builder;
	}

	/*
		for each block
			create an interface called *Builder
			populate the interface with the 'any' methods and the 'only' methods

			create a class called *Helper
			populate the class with all of the methods in the block, returning void

			create an implementation of the base interface called Impl*Builder

			for all other methods
				create interfaces which extend the base with the subsets
	 */

	private void processBlock(JPackage thePackage, BlockData block) {
		Set<MethodData> dynamicMethods = new HashSet<MethodData>();
		Set<MethodData> baseMethods = new HashSet<MethodData>();

		// create an interface called *Builder
		JDefinedClass iBuilder = generateBuilderInterface(thePackage, block.blockName);

		// create an interface called *Helper (user will implement this for behavior)
		JDefinedClass iHelper = getOrCreateInterface(thePackage, block.blockName + "Helper");
	
		// create a class called Impl*Builder
		JDefinedClass cBuilder = getOrCreateClass(thePackage, "Impl"+block.blockName+"Builder");

		for (MethodData method : block.methods) {
			// any' methods and the 'only' methods (required methods)
			if (method.isRequired()) {
				// populate the interface with the method
				addMethod(iBuilder, model.VOID, JMod.NONE, method);

				// add the methods for the base impl class
				baseMethods.add(method);
			// otherwise, it's dynamic, so remember this
			} else {
				dynamicMethods.add(method);
			}

			// populate the helper with all of the methods in the block, returning void
			addMethod(iHelper, model.VOID, JMod.NONE, method);
		}

		// set up the base interface
		// TODO extract a 'generateInterface' method?

		// set up the base impl class
		generateBaseImplClass(cBuilder, iBuilder, iHelper, baseMethods);
		
		// create every builder interface and associated implementation
		Set<Set<MethodData>> combinations = next(dynamicMethods);
		for (Set<MethodData> combination : combinations) {
			String generatedName = getGeneratedName(block.blockName+"Builder", combination);
			boolean isBase = combination.isEmpty();

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = getOrCreateInterface(thePackage, generatedName);

			// extend the base interface, return type of self
			if (!isBase) {
				iSubset._implements(iBuilder.narrow(iSubset));
			}

			// add methods to interface
			for (MethodData method : combination) {
				JType returnType = getReturnType(block.blockName, method, combination);
				addMethod(iSubset, returnType, JMod.NONE, method);
			}

			// make the Impl* class
			JDefinedClass cSubset = generateImplClass(
				thePackage, generatedName,
				isBase ? null : cBuilder, iSubset, iHelper,
				combination, block
			);	
		}
		
		// process nested blocks
		for (BlockData child : block.blocks) {
			// add the constructor for this block
			// return type is current block with param
			addMethod(iBuilder, iBuilder.typeParams()[0], JMod.NONE, child.constructor);
			
			processBlock(thePackage, child);
		}
	}
	
	private void generateBaseImplClass(JDefinedClass base, JDefinedClass _interface, JDefinedClass helper, Set<MethodData> methods) {
		// make generic with return type
		JTypeVar tv = base.generify(RETURN_TYPE_PARAM);

		// implement the interface
		base._implements(_interface.narrow(tv));
		
		// final *Helper _helper;
		JFieldVar fHelper = base.field(JMod.PROTECTED+JMod.FINAL, helper, "_helper");

		// constructor
		JMethod constructor = base.constructor(JMod.PUBLIC);
		JVar pHelper = constructor.param(helper, "helper");

		// _helper = helper
		constructor.body().assign(fHelper, pHelper);

		// add inbuilt methods
		base.method(JMod.PUBLIC, model.VOID, "build");

		// add methods
		for (MethodData method : methods) {
			JMethod m = addMethod(base, model.VOID, JMod.PUBLIC, method);
			addHelperCall(m, method);
		}
	}
	
	private void addHelperCall(JMethod _method, MethodData method) {
		JFieldRef _helper = JExpr.ref("_helper");
		JInvocation helperCall = _helper.invoke(_method.name());

		// normal args
		for (JVar param : _method.listParams()) {
			helperCall.arg(param);
		}

		// vararg
		if (_method.listVarParam() != null) {
			helperCall.arg(_method.listVarParam());
		}
		
		_method.body().add(helperCall);
	}

	private JDefinedClass generateImplClass(
		JPackage thePackage, String name,
		JDefinedClass base, JDefinedClass _interface, JDefinedClass helper,
	    Set<MethodData> methods, BlockData block
	){
		JDefinedClass _class = getOrCreateClass(thePackage, "Impl"+name);

		// extend the base implementation if not already the base, and implement type
		if (base != null) {
			_class._extends(base.narrow(_interface));
			_class._implements(_interface);
		}

		// add constructor (only handle the child constructors here)
		if (base != null) {
			//JMethod superConstructor = base.getConstructor(new JType[]{helper});
			//constructor.body().add(JExpr.invoke(JExpr._super(), "").arg(pHelper));

			// TODO figure out the proper way to call a superclass constructor

			JMethod constructor = _class.constructor(JMod.PUBLIC);
			JVar pHelper = constructor.param(helper, "helper");
			constructor.body().directStatement("super(helper);");
		}

		// add all methods
		for (MethodData method : methods) { // methods will be empty when base is null
			JType returnType = getReturnType(block.blockName, method, methods);

			// add the method
			JMethod m = addMethod(_class, returnType, JMod.PUBLIC, method);
		
			// -- set the method body --
			
			// first, a pass-through to the helper
			addHelperCall(m, method);

			// then, return the appropriate new instance (skip void)
			if (returnType != model.VOID) {
				m.body()._return(JExpr._new(returnType).arg(JExpr.ref("_helper")));
			}
		}
		
		return _class;
	}
	
	private JType getReturnType(String blockName, MethodData method, Set<MethodData> allMethods) {
		JType returnType;

		// if terminal, return type is void
		if (method.isTerminal) {
			returnType = model.VOID;

		// else return type is 'minus method'
		} else {
			Set<MethodData> minusMethod = new HashSet<MethodData>(allMethods);
			minusMethod.remove(method);

			// only add back if it's not the last instance
			if (method.maxOccurrances > 1) {
				MethodData m = method.copy();
				m.maxOccurrances = m.maxOccurrances - 1;
				minusMethod.add(m);
			}

			String typeString = getGeneratedName("Impl"+blockName+"Builder", minusMethod);
			returnType = getOrCreateClass(thePackage, typeString);
		}
		
		return returnType;
	}
	
	private JMethod addMethod(JDefinedClass _class, JType returnType, int mods, MethodData method) {
		MethodParser parsed = new MethodParser(method.methodSignature);
		JMethod m = _class.method(mods, returnType, parsed.methodName);

		// regular params
		for (Map.Entry<String, String> entry : parsed.params.entrySet()) {
			JType clazz = getType(entry.getValue());
			m.param(clazz, entry.getKey());
		}

		// varargs
		if (parsed.varargType != null) {
			JType clazz = getType(parsed.varargType);
			m.varParam(clazz, parsed.varargName);
		}
		
		return m;
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
	
	private JDefinedClass getOrCreateClass(JPackage _package, String name) {
		JDefinedClass _class = classes.get(name);
		if (_class == null) {
			try {
				_class = _package._class(JMod.PUBLIC, name);
				classes.put(name, _class);
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}
		}

		return _class;
	}

	private JType getType(String name) {
		JType clazz;
		
		if ("boolean".equals(name)) {
			clazz = model.BOOLEAN;
		} else if ("int".equals(name)) {
			clazz = model.INT;
		} else if ("byte".equals(name)) {
			clazz = model.BYTE;
		} else if ("short".equals(name)) {
			clazz = model.SHORT;
		} else if ("float".equals(name)) {
			clazz = model.FLOAT;
		} else if ("double".equals(name)) {
			clazz = model.DOUBLE;
		} else {
			// expect fully qualified class name
			clazz = model._getClass(name);

			// try java.lang package
			if (clazz == null) {
				clazz = model.ref("java.lang."+name);
			}

			// fail
			if (clazz == null) {
				throw new RuntimeException("Could not find type reference for type name '"+ name +"'.");
			}
		}
		
		return clazz;
	}
	
	private String getGeneratedName(String className, Set<MethodData> methods) {
		// change set to a sorted set for consistency
		methods = new TreeSet<MethodData>(methods);
		
		StringBuilder name = new StringBuilder();
		name.append(className);

		// TODO change to mapped names after debugging (if condensed parameter)

		for (MethodData method : methods) {
			MethodParser parsed = new MethodParser(method.methodSignature);
			name.append("_").append(parsed.methodName);
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
}
