package unquietcode.tools.flapi.builder;

import unquietcode.Pair;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface DescriptorHelper {
	void _setDescriptorName(String name);
	void _setDescriptorMethod(String method);

	void setPackage(String packageName);
	void showLog(boolean value);

	MethodHelper addMethod(String methodSignature);
	Pair<MethodHelper,BlockHelper> startBlock(String blockName, String methodSignature);
}
