
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public interface MethodHelper {


    void once();

    void last();

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void atMost(int num);

    void atLeast(int num);

    void any();

    void between(int atMost, int atLeast);

}
