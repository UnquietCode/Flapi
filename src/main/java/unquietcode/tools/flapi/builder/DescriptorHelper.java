package unquietcode.tools.flapi.builder;


import unquietcode.tools.flapi.Descriptor;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface DescriptorHelper {
	Descriptor _getReturnValue();

	void setPackage(String packageName);
	void showLog(boolean value);

	List<Object> addMethod(String methodSignature);
	List<Object> startBlock(String blockName, String methodSignature);
}
