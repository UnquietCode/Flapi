package unquietcode.tools.flapi;

import java.util.ArrayList;
import java.util.List;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/
public class DescriptorData {
	String descriptorName;
	String descriptorMethod;
	String packageName;
	Boolean showLog;

	final List<MethodData> methods = new ArrayList<MethodData>();
	final List<BlockData> blocks = new ArrayList<BlockData>();
}
