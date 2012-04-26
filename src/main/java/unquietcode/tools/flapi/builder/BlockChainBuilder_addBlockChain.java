
package unquietcode.tools.flapi.builder;


public interface BlockChainBuilder_addBlockChain<_ReturnType >{


    _ReturnType addBlockReference(String blockName);

    BlockBuilder_addBlockChain<_ReturnType> startBlock(String methodSignature, String blockName);

    BlockChainBuilder_addBlockChain<BlockChainBuilder<_ReturnType>> addBlockChain();

}
