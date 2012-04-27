
package unquietcode.tools.flapi.builder;


public interface BlockChainBuilder_addBlockChain<_ReturnType >{


    _ReturnType addBlockReference(String blockName);

    BlockBuilder<_ReturnType> startBlock(String blockName, String methodSignature);

    BlockChainBuilder_addBlockChain<BlockChainBuilder<_ReturnType>> addBlockChain();

}
