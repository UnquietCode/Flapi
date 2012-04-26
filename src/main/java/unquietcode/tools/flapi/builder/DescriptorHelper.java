
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.ObjectWrapper;

public interface DescriptorHelper {

	Descriptor _getReturnValue();

    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void setDescriptorName(String descriptorName);

    void showLog(boolean value);

    void setReturnType(Class returnType);

    void setStartingMethodName(String methodName);

    void setPackage(String packageName);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void build();

}
