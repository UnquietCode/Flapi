
package unquietcode.tools.flapi.builder;


public interface BlockBuilder_addBlockChain<_ReturnType >{


    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>> addBlockReference(String blockName, String methodSignature);

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>> addMethod(String methodSignature);

    _ReturnType endBlock();

	MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<BlockBuilder_addBlockChain<_ReturnType>>> startBlock(String blockName, String methodSignature);

    BlockChainBuilder_addBlockChain<BlockBuilder<_ReturnType>> addBlockChain();

}
