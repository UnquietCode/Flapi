
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.ObjectWrapper;

public interface DescriptorHelper {


    Descriptor _getReturnValue();

    void showLog(boolean value);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void setReturnType(Class returnType);

    void setDescriptorName(String descriptorName);

    void setPackage(String packageName);

    void setStartingMethodName(String methodName);

    void build();

}
