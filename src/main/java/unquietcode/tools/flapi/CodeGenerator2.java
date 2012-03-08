package unquietcode.tools.flapi;

import com.sun.codemodel.*;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Benjamin Fagin
 * @version 02-15-2012
 */
public class CodeGenerator2 {
	private static final int MAX_METHODS = Integer.MAX_VALUE - 1000;
	private static final String NAME_RETURN_TYPE_PARAM = "_ReturnType";
	private static final String NAME_BUILDER = "Builder";
	private static final String NAME_GENERATOR = "Generator";
	
	
	//==o==o==o==o==o==o==| Operational |==o==o==o==o==o==o==//    
	
	JCodeModel model = new JCodeModel();	
	JPackage thePackage;
	Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();

	public JCodeModel generateCodeModel(DescriptorData descriptor) {
		// create the package which will hold everything
		String packageName = descriptor.packageName;
		if (packageName == null) { packageName = ""; }
		thePackage = model._package(packageName);
		
		// check for possible collisions
		checkForCollisions(descriptor);
		
		// stub the top level builder interface
		generateTopLevelInterface(descriptor);
		
		// stub the top level builder implementation
		generateTopLevelImplClass(descriptor);
		
		// generate the *Generator class
		generateGeneratorClass(thePackage, descriptor);

		// process the top level block
		processBlock(descriptor.block);
		
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

	private void processBlock(BlockData block) {
		// -- base level --
		
		// create an interface called *Builder
		JDefinedClass iBuilder = getOrCreateInterface(block.blockName + "Builder");
		iBuilder.generify("_ReturnType");

		// create an interface called *Helper (user will implement this for behavior)
		JDefinedClass iHelper = getOrCreateInterface(block.blockName + "Helper");

		// create a class called Impl*Builder
		JDefinedClass cBuilder = generateBaseImplClass(block.blockName, iBuilder, iHelper);

		// build the class
		Set<MethodData> dynamicMethods = new HashSet<MethodData>();

		for (MethodData method : block.methods) {

			// any' methods and the 'only' methods (required methods)
			if (method.isRequired()) {

				// add the method to the interface (returning its self type)
				addMethod(iBuilder, iBuilder.typeParams()[0], JMod.NONE, method);
		
				// add the method to the base class (returning interface self type)
				JMethod m = addMethod(cBuilder, iBuilder.typeParams()[0], JMod.PUBLIC, method);
				m.body().add(makeHelperCall(m, method));
			
			// otherwise, it's dynamic, so remember this
			} else {
				dynamicMethods.add(method);
			}

			// populate the helper with all of the methods in the block, returning void
			addMethod(iHelper, model.VOID, JMod.NONE, method);
		}
		
		// -- iterative level --
		
		// create every builder interface and associated implementation
		for (Set<MethodData> combination : makeCombinations(dynamicMethods)) {
			String generatedName = getGeneratedName(block.blockName+"Builder", combination);
			boolean isBase = combination.isEmpty();

			// make the interface (the empty one should be the only already created one)
			JDefinedClass iSubset = generateInterface(generatedName, iBuilder, block, combination);

			// make the Impl* class
			JDefinedClass cSubset = generateImplClass(
				generatedName,
				isBase ? null : cBuilder, iSubset, iHelper,
				combination, block
			);	
		}
		
		// -- nested level --

		for (BlockData child : block.blocks) {
			// if child already exists, then this is an error!
			// TODO throw exception

			// resolve block references (error if not found)
			// if there is a 'first' block or 'first' block reference (error if more than one)
				// add the method in such a way that it returns the nested element
				// remove it from the blocks pile
			// for all other blocks, process as nested


			addNestedBlock(block, child, iBuilder, cBuilder);
		}
	}
	
	private void addNestedBlock(BlockData parent, BlockData child, JDefinedClass iParent, JDefinedClass cParent) {
		// add the constructor for the block to the interface

		// TODO
		// if the child constructor

		JDefinedClass iChild = getOrCreateInterface(child.blockName+"Builder");
		addMethod(iParent, iChild.narrow(iParent.typeParams()[0]), JMod.NONE, child.constructor);

		// add the constructor to the implementation
		JDefinedClass cChild = getOrCreateClass("Impl"+child.blockName+"Builder");
		JDefinedClass hChild = getOrCreateInterface(child.blockName+"Helper");

		JMethod m = addMethod(cParent, iChild.narrow(iParent.typeParams()[0]), JMod.NONE, child.constructor);
		JVar helper = m.body().decl(hChild, "helper", makeHelperCall(m, child.constructor));
		m.body()._return(JExpr._new(cChild.narrow(iParent.typeParams()[0]))
				.arg(helper)
				.arg(JExpr.ref("_returnValue"))
		);		
	}
	
	
	
	
	
	//==o==o==o==o==o==o==| Iterative Level |==o==o==o==o==o==o==//    
	
	private JDefinedClass generateInterface(String name, JDefinedClass _interface, BlockData block, Set<MethodData> methods) {
		// make the interface (the empty one should be the only already created one)
		JDefinedClass iSubset = getOrCreateInterface(name);

		// extend the base interface, return type of self (skip base)
		if (!methods.isEmpty()) {
			iSubset._implements(_interface.narrow(iSubset));
		}

		// add methods to interface
		for (MethodData method : methods) {
			JType returnType = getReturnType(block.blockName+"Builder", method, methods, true);
			addMethod(iSubset, returnType, JMod.NONE, method);
		}
		
		return iSubset;
	}
	
	private JDefinedClass generateImplClass(
		String name,
		JDefinedClass base, JDefinedClass _interface, JDefinedClass helper,
	    Set<MethodData> methods, BlockData block
	){
		JDefinedClass _class = getOrCreateClass("Impl"+name);

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
			JType returnType = getReturnType(block.blockName+"Builder", method, methods, false);

			// add the method
			JMethod m = addMethod(_class, returnType, JMod.PUBLIC, method);
		
			// -- set the method body --
			
			// first, a pass-through to the helper
			m.body().add(makeHelperCall(m, method));

			// then, return the appropriate new instance
			m.body()._return(JExpr._new(returnType)
					.arg(JExpr.ref("_helper"))
					.arg(JExpr._null()) // TODO proper constructor params
			);
		}
		
		return _class;
	}
	
	//==o==o==o==o==o==o==| Base Level |==o==o==o==o==o==o==//    

	private JDefinedClass generateBaseImplClass(String name, JDefinedClass _interface, JDefinedClass helper) {
		JDefinedClass base = getOrCreateClass("Impl"+name+"Builder");

		// make generic with return type
		JTypeVar tv = base.generify(NAME_RETURN_TYPE_PARAM);

		// implement the interface
		base._implements(_interface.narrow(tv));

		// protected final *Helper _helper;
		JFieldVar fHelper = base.field(JMod.PROTECTED + JMod.FINAL, helper, "_helper");

		// @SuppressWarnings("unchecked")
		// private final _ReturnType _self = (_ReturnType) this;
		JFieldVar fSelf
				= base.field(JMod.PRIVATE + JMod.FINAL, tv, "_self", JExpr.cast(tv, JExpr._this()));
		fSelf.annotate(SuppressWarnings.class).param("value", "unchecked");

		// protected final _ReturnType _returnValue;
		JFieldVar fReturnValue
				= base.field(JMod.PROTECTED + JMod.FINAL, tv, "_returnValue");
		
		// constructor
		JMethod constructor = base.constructor(JMod.PUBLIC);
		JVar pHelper = constructor.param(helper, "helper");
		JVar pReturnValue = constructor.param(tv, "returnValue");

		// _helper = helper
		constructor.body().assign(fHelper, pHelper);

		// if returnValue != null
		//     _returnValue = returnValue;
		// else
		//     _returnValue = (_ReturnType) this;
		JConditional cif = constructor.body()._if(pReturnValue.ne(JExpr._null()));
		cif._then().assign(fReturnValue, pReturnValue);
		cif._else().assign(fReturnValue, fSelf);
		
		
		// TODO where is the missing addMethod and startBlock stuff?

		return base;
	}	
	
	//==o==o==o==o==o==o==| Top Level (decorative) |==o==o==o==o==o==o==// 

	private JDefinedClass generateGeneratorClass(JPackage thePackage, DescriptorData descriptor) {
		// figure out the top level interface name from the methods
		Set<MethodData> dynamicMethods = new HashSet<MethodData>();
		for (MethodData method : descriptor.block.methods) {
			if (!method.isRequired()) {
				dynamicMethods.add(method);
			}
		}
		String topName = getGeneratedName(descriptor.block.blockName+"Builder", dynamicMethods);
		JDefinedClass iTop = getOrCreateInterface(topName);
		JDefinedClass generator = getOrCreateClass(descriptor.block.blockName + "Generator");
		JDefinedClass descriptorHelper = getOrCreateInterface(descriptor.block.blockName + "Helper");
		JDefinedClass rType = getOrCreateClass("Impl"+topName);

		// -- add the constructor methods --

		JMethod createWithString = generator.method(JMod.PUBLIC+JMod.STATIC, iTop, descriptor.descriptorMethod);
		JVar pName = createWithString.param(model.ref(String.class), "name");
		JVar pMethod = createWithString.param(model.ref(String.class), "method");
		JVar pHelper = createWithString.param(descriptorHelper, "helper");

		// check arguments

		// if (method == null || method.trim().isEmpty())
		//      throw new IllegalArgumentException("Name cannot be empty.");
		// else
		//      method = method.trim();
		JConditional _if = createWithString.body()
				._if(pMethod.eq(JExpr._null()).cor(pMethod.invoke("trim").invoke("isEmpty")));
		_if._then()._throw(JExpr._new(model.ref(IllegalArgumentException.class)).arg("Name cannot be empty."));
		_if._else().assign(pMethod, pMethod.invoke("trim"));
		createWithString.body().directStatement(" ");

		// if (helper == null)
		//      throw new IllegalArgumentException("Helper cannot be null.");
		_if = createWithString.body()
				._if(pHelper.eq(JExpr._null()));
		_if._then()._throw(JExpr._new(model.ref(IllegalArgumentException.class)).arg("Helper cannot be null."));
		createWithString.body().directStatement(" ");

		// set and return
		createWithString.body().invoke(pHelper, "_setDescriptorName").arg(pName);
		createWithString.body().invoke(pHelper, "_setDescriptorMethod").arg(pMethod);
		createWithString.body()._return(JExpr._new(rType).arg(pHelper));

		// create the second constructor, which uses the default string
		JMethod create = generator.method(JMod.PUBLIC+JMod.STATIC, iTop, descriptor.descriptorMethod);
		pName = create.param(model.ref(String.class), "name");
		pHelper = create.param(descriptorHelper, "helper");

		create.body()._return(JExpr.invoke(createWithString).arg(pName).arg("create").arg(pHelper));

		return generator;
	}	
	
	private JDefinedClass generateTopLevelInterface(DescriptorData descriptor) {
		JDefinedClass builder = getOrCreateInterface(descriptor.block.blockName+"Builder");

		// add build method
		builder.method(JMod.NONE, model.ref(Descriptor.class), "build");

		return builder;
	}	
	
	private JDefinedClass generateTopLevelImplClass(DescriptorData descriptor) {
		JDefinedClass base = getOrCreateClass("Impl"+descriptor.block.blockName+"Builder");

		// add inbuilt methods
		base.method(JMod.PUBLIC, model.VOID, "build")
				.body()._return(JExpr._new(model.ref(Descriptor.class)).arg(JExpr.ref("_helper")));

		return base;
	}	
	
	//==o==o==o==o==o==o==| Helpers |==o==o==o==o==o==o==//    

	private void checkForCollisions(DescriptorData descriptor) {
		// TODO a proper check
		//_checkForCollisions(blocks, new HashSet<String>());
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

	private JInvocation makeHelperCall(JMethod _method, MethodData method) {
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

		return helperCall;
	}	
	
	private JClass getReturnType(String name, MethodData method, Set<MethodData> allMethods, boolean interfaceDesired) {
		Set<MethodData> minusMethod = new HashSet<MethodData>(allMethods);
		minusMethod.remove(method);

		// only add back if it's not the last instance
		if (method.maxOccurrances > 1) {
			MethodData m = method.copy();
			m.maxOccurrances = m.maxOccurrances - 1;
			minusMethod.add(m);
		}

		JClass iType = getOrCreateInterface(getGeneratedName(name, minusMethod));
		if (interfaceDesired) {
			// if it's the base class, we need the self-type
			if (minusMethod.isEmpty()) {
				return iType.narrow(iType);
			} else {
				return iType;
			}
		}
		
		JClass cType = getOrCreateClass(getGeneratedName("Impl"+name, minusMethod));
		if (minusMethod.isEmpty()) {
			return cType.narrow(iType);
		} else {
			return cType;
		}
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

	private JDefinedClass getOrCreateInterface(String name) {
		JDefinedClass _interface = interfaces.get(name);
		if (_interface == null) {
			try {
				_interface = thePackage._interface(name);
				interfaces.put(name, _interface);
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}
		}

		return _interface;
	}

	private JDefinedClass getOrCreateClass(String name) {
		JDefinedClass _class = classes.get(name);
		if (_class == null) {
			try {
				_class = thePackage._class(JMod.PUBLIC, name);
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

	static Set<Set<MethodData>> makeCombinations(Set<MethodData> methods) {
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
}
