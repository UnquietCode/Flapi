
package unquietcode.tools.flapi.builder;


public interface MethodBuilder<_ReturnType >{


    _ReturnType any();

    _ReturnType atLeast(int num);

    _ReturnType atMost(int num);

    _ReturnType between(int atMost, int atLeast);

    _ReturnType last();

    _ReturnType once();

}
