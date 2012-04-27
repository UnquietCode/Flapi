
package unquietcode.tools.flapi.builder;


public interface BlockChainBuilder<_ReturnType >{


    _ReturnType addBlockReference(String blockName);

    BlockBuilder<_ReturnType> startBlock(String blockName, String methodSignature);

}
