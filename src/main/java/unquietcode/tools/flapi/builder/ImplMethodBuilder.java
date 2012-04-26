
package unquietcode.tools.flapi.builder;


public class ImplMethodBuilder
    implements MethodBuilder
{

    protected final MethodHelper _helper;
    protected final Object _returnValue;

    ImplMethodBuilder(MethodHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public Object any() {
        _helper.any();
         
        return _returnValue;
    }

    public Object atLeast(int num) {
        _helper.atLeast(num);
         
        return _returnValue;
    }

    public Object atMost(int num) {
        _helper.atMost(num);
         
        return _returnValue;
    }

    public Object between(int atMost, int atLeast) {
        _helper.between(atMost, atLeast);
         
        return _returnValue;
    }

    public Object last() {
        _helper.last();
         
        return _returnValue;
    }

    public Object once() {
        _helper.once();
         
        return _returnValue;
    }

}
