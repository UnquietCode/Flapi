package unquietcode.tools.flapi.outline;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JInvocation;
import unquietcode.Pair;
import unquietcode.tools.flapi.BlockReference;
import unquietcode.tools.flapi.generator.AbstractGenerator;

import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-09-2012
 */
public class BlockOutline implements Outline {
	// name of the block
	public String name;

	// nested blocks
	public List<BlockOutline> blocks = new ArrayList<BlockOutline>();
	
	// constructor, used by parent to create this block
	public MethodOutline constructor;
	
	// block references
	public final List<BlockReference> blockReferences = new ArrayList<BlockReference>();

	// methods
	public final Set<MethodOutline> methods = new HashSet<MethodOutline>();

	public String getTopLevelImplementation() {
		return "Impl"+ AbstractGenerator.getGeneratedName(name + "Builder", getDynamicMethods());
	}

	public String getTopLevelInterface() {
		return AbstractGenerator.getGeneratedName(name + "Builder", getDynamicMethods());
	}
	
	public String getBaseImplementation() {
		return "Impl"+name+"Builder";
	}
	
	public String getBaseInterface() {
		return name+"Builder";
	}
	
	public String getHelperInterface() {
		return name+"Helper";
	}
	
	public String getGeneratorImplmentation() {
		return name+"Generator";
	}
	

	public Set<MethodOutline> getRequiredMethods() {
		Set<MethodOutline> required = new TreeSet<MethodOutline>();
		
		for (MethodOutline method : methods) {
			if (method.isRequired()) {
				required.add(method);
			}
		}
		
		return required;
	}

	public Set<MethodOutline> getDynamicMethods() {
		Set<MethodOutline> dynamic = new TreeSet<MethodOutline>();

		for (MethodOutline method : methods) {
			if (!method.isRequired()) {
				dynamic.add(method);
			}
		}

		return dynamic;
	}
/*
	void prepare() {
		// Split up methods into required and dynamic.
		// Required means that the method appears in all combinations,
		// whereas dynamic means they could be there or not be there
		// depending on the the state.


		for (MethodOutline method : methods) {
			if (method.isRequired()) {
				requiredMethods.add(method);
			} else {
				dynamicMethods.add(method); 
			}
		}
		
		// each required method creates a path from self to self
		// Name<_ReturnType> requiredMethodSignature

		for (MethodOutline requiredMethod : requiredMethods) {
			PathSegment path = new PathSegment();
			MethodOutline method = new MethodOutline();
			method.methodSignature = requiredMethod.methodSignature;
			String returnType = name+"<_ReturnType>"

		}
		
		
		
		for (BlockOutline block : blocks) {
			if (block.constructor.isRequired()) {
				
			}
		}
	}*/
	
	



	public void generate() {
		// generate a list of paths from this block base state
	}



/*
	private Set<PathSegment> generatePaths() {
		Set<PathSegment> paths = new HashSet<PathSegment>();

		// nested blocks add a path to the block's constructor
		// the constructor could return the block, or something else. 


		// for every nested block, 
		//    discover the constructor for that block, and what it returns


		for (BlockOutline block : blocks) {
			PathSegment path = new PathSegment();

			// ReturnValue<Self<_ReturnType>> constructor(..)

			MethodOutline method = new MethodOutline();
			method.methodSignature = block.constructor.methodSignature;

			//name+
			// DescriptorBuilderX extends ImplDescriptorBuilder implements DescriptorBuilder<DescriptorBuilderX>

			//ImplDescriptorBuilderX extends ImplDescriptorBuilder<DescriptorBuilder_X> implements DescriptorBuilderX {

		}
		
		return null;

	}*/


	/*
			
		
		
		
		 */





}
