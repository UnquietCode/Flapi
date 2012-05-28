
package unquietcode.tools.flapi.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 12:09:05 CDT using version 0.2
 * 
 */
public class ObjectWrapper<_Type >{

    private _Type _value;

    public ObjectWrapper() {
    }

    public ObjectWrapper(_Type value) {
        _value = value;
    }

    public void set(_Type value) {
        _value = value;
    }

    public _Type get() {
        return _value;
    }

    @Override
    public String toString() {
        if (_value == null) {
            return null;
        } else {
            return _value.toString();
        }
    }

}
