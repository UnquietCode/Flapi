
package unquietcode.tools.flapi.builder;


public interface BlockChainBuilder<_ReturnType >{


    _ReturnType addBlockReference(String blockName);

    BlockBuilder_addBlockChain<_ReturnType> startBlock(String methodSignature, String blockName);

}
