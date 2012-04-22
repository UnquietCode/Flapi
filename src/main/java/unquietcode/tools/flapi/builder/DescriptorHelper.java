package unquietcode.tools.flapi.builder;


import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface DescriptorHelper {
	void _setDescriptorName(String name);
	void _setDescriptorMethod(String methodName);

	void setPackage(String packageName);
	void showLog(boolean value);

	List<Object> addMethod(String methodSignature);
	List<Object> startBlock(String blockName, String methodSignature);
}
