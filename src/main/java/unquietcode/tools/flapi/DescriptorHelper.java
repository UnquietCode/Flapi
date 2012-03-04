package unquietcode.tools.flapi;


import java.util.ArrayList;
import java.util.List;

import static unquietcode.tools.flapi.DescriptorClasses.*;

/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public class DescriptorHelper {
	List<BlockData> blocks = new ArrayList<BlockData>();
	List<MethodData> methods = new ArrayList<MethodData>();
	String packageName;
	Boolean showLog;
	String anotherOption;

	DescriptorHelper() {
		// nothing for now
	}


	public static DescriptorInterfaces.DescriptorBuilder_showLog_anotherOption_setPackage create() {
		return new DescriptorClasses.ImplDescriptorBuilder_showLog_anotherOption_setPackage(new DescriptorHelper());
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

	protected void _addBlock(BlockData block) {
		blocks.add(block);
	}
}