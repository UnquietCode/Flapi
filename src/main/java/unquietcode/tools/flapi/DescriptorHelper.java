package unquietcode.tools.flapi;


import java.util.ArrayList;
import java.util.List;

import static unquietcode.tools.flapi.DescriptorClasses.*;

/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public class DescriptorHelper {
	List<Block> blocks = new ArrayList<Block>();
	String packageName;
	Boolean showLog;
	String anotherOption;

	DescriptorHelper() {
		// nothing for now
	}


	public static ImplDescriptorBuilder_showLog_anotherOption_setPackage create() {
		return new DescriptorClasses.ImplDescriptorBuilder_showLog_anotherOption_setPackage(new DescriptorHelper());
	}



	protected static class Block {
		protected String blockName;
		protected Method constructor;

		private final List<Method> methods = new ArrayList<Method>();
		private final List<Block> blocks = new ArrayList<Block>();

		protected void _addBlock(Block block) {
			blocks.add(block);
		}

		protected void _addMethod(Method method) {
			methods.add(method);
		}
	}

	protected static class Method {
		protected int minOccurrances;
		protected int maxOccurrances;
		protected String methodSignature;
	}


	protected void showLog(boolean neal) {
		this.showLog = neal;
	}

	protected void setPackage(String packageName) {
		this.packageName = packageName;
	}

	protected void anotherOption(String anotherOption) {
		this.anotherOption = anotherOption;
	}

	protected void _addBlock(Block block) {
		blocks.add(block);
	}
}