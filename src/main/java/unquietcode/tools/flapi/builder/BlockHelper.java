
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public interface BlockHelper {


    void addMethod(String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void addBlockReference(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1);

    void endBlock();

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<MethodHelper> _helper1, ObjectWrapper<BlockHelper> _helper2);

}
