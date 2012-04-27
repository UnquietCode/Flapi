
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public interface MethodHelper {


    void between(int atMost, int atLeast);

    void atMost(int num);

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void atLeast(int num);

    void any();

    void last();

    void once();

}
