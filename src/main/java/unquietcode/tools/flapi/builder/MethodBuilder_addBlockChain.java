
package unquietcode.tools.flapi.builder;


public interface MethodBuilder_addBlockChain<_ReturnType >{


    _ReturnType any();

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num);

    _ReturnType between(int atMost, int atLeast);

    _ReturnType last();

    _ReturnType once();

    BlockChainBuilder_addBlockChain<MethodBuilder<_ReturnType>> addBlockChain();

}
