package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-23-2012
 */
public interface MethodBuilder_addBlockChain<_ReturnType> {
	_ReturnType once();
	_ReturnType any();
	_ReturnType last();
	_ReturnType atLeast(int num);
	_ReturnType atMost(int num);
	_ReturnType between(int atLeast, int atMost);

	BlockChainBuilder_addBlockChain<MethodBuilder<_ReturnType>> addBlockChain();
}
